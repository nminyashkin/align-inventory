# align-inventory

## running
To run service, execute "./gradlew -i clean service:bootRun" in project root directory. Service runs on port 9000

## authentication
Service requires "HTTP basic" authentication

Admin user: admin/admin

Ordinal user: user/user

## remarks
As RESTful implementation was not required, service has not "root" endpoint with links to servoce resources

## endpoints
Service endpoints are:

http://localhost:9000/inventory/findByBrand/{brand}

http://localhost:9000/inventory/findByName/{name}

http://localhost:9000/inventory/findByBrandAndName/{brand}/{name}

http://localhost:9000/inventory/findLiftovers

http://localhost:9000/inventory/add/{brand}/{name}/{quantity}

http://localhost:9000/inventory/update/{id}/{brand}/{name}/{quantity}

http://localhost:9000/inventory/delete/{id}

I hope endpoint URIs are self-describing

Service contains a "non-required" endpoint (http://localhost:9000/inventory/findAll). This endpoint was developed for debugging purposes
