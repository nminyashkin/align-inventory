# align-inventory

## running
To run service, execute "./gradlew -i clean service:bootRun" in project root directory. Service runs on port 9000

## authentication
Service requires "HTTP basic" authentication

Admin user: admin/admin

Ordinal user: user/user

## remarks
As RESTful implementation was not required, service has not "root" endpoint with links to service resources

## endpoints
Service endpoints are:

http://localhost:9000/inventory/findByBrand/{brand} (GET HTTP method expected)

http://localhost:9000/inventory/findByName/{name} (GET HTTP method expected)

http://localhost:9000/inventory/findByBrandAndName/{brand}/{name} (GET HTTP method expected)

http://localhost:9000/inventory/findLiftovers (GET HTTP method expected)

http://localhost:9000/inventory/add/{brand}/{name}/{quantity} (POST HTTP method expected)

http://localhost:9000/inventory/update/{id}/{brand}/{name}/{quantity} (POST HTTP method expected)

http://localhost:9000/inventory/delete/{id} (POST HTTP method expected)

I hope endpoint URIs are self-describing

Service contains a "non-required" endpoint (http://localhost:9000/inventory/findAll). This endpoint was developed for debugging purposes
