# MavenHelloWorld

# Prerequisite
1. Install vagrant

This is our product which we will be testing in all other testing tutorial, this has three parts
1. static html page /helloworld/helloworld.html
2. a dynamic web page which returns current time stamp /helloworld/FirstServlet
3. a rest API which handles post operation and writes the "name" & "value" parameter received to the DB


# Deploy this product in a VM
1. run the Vagrant file found in this repository
2. This will deploy the product in a VM whose ip address is 192.168.33.14
3. Check whether you are getting proper time stamp if you access http://192.168.33.14:8080/helloworld/FirstServlet
