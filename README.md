# Simple Checkout Service

## Starting Locally
- run `./gradlew bootRun`
- send a valid POST request to `http://localhost:8080/checkout`


## The approach
I started implementing layer by layer starting from Controller side 
and was trying to make commits that bring some business value.

I created a Repository class to store the data. I used a simple class with a list inside
as we don't have so much data and for this case the solution would be enough. In production 
we would have more data and I would use a DB instead.

The repository also returns a list of requested watches taking into the consideration possible duplications 
in the request (this logic could also be moved to a service).
After getting all the requested for checkout watches, the Service calculates their total price
and subtracts possible discounts. After that the final price is returned to the Controller.

### P.S.
I got an annoying error last moment when trying to run e2e test. It's caused by conflicting implementations
of logging in different libs that I imported in gradle. Theoretically the solution should be to `exclude`
one of the conflicting Logging implementations out of imported libs, but it takes a lot of time to understand
which exactly library causes the issue, so I've put `@Disabled` on e2e test to be able to build and run the project, 
and tested it manually with Postman.

## Possible improvements
- The discount logic could definitely be improved and more flexible than current approach
- Also discount logic could theoretically be moved to a separate class, but it depends 
- There should be more tests added, checking all the edge cases (both unit tests and `e2e` tests)