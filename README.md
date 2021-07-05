# MavenHelloWorld

# Prerequisite
1. Install vagrant 

# Deploy the product in a VM
1. run the Vagrant file found in this repository
2. This will deploy the product in a VM whose ip address is 192.168.33.14
3. Check whether you are getting proper time stamp if you access http://192.168.33.14:8080/helloworld/FirstServlet
4. ssh into the VM, goto mysql using following command
```
mysql -u root -p
```
password: 12345678

create table using following command:
```
CREATE TABLE TEST
(ID INTEGER PRIMARY KEY AUTO_INCREMENT,
NAME CHAR(10),
VAL CHAR(20));
```
5. open post man and check if you can properly post in http://192.168.33.14:8080/helloworld/insert
you should get proper response back like below

![response message](/src/images/1.png)

6. A new entry in Test table should be made after the post request like below.

![response message](/src/images/2.png)


## Contents of the product
This is our product which we will be testing in all other testing tutorial, this has three parts
1. static html page /helloworld/helloworld.html
2. a dynamic web page which returns current time stamp /helloworld/FirstServlet
3. a rest API which handles post operation and writes the "name" & "value" parameter received to the DB

# Liquibase

## What is liquibase?

- open source library to track database changes
- supports XML, JSON, and YAML files
- The linear database versions fails when different developers add different versions to the database concurrently. 
provides a solution to this issue by using a unique identification scheme for each changeset that is designed to guarantee uniqueness across developers.

For more reference see to the slide in https://drive.google.com/file/d/19UUIHzobwXTqw_uv-qiw6YTTpTj3UL_N/view?usp=sharing

## Installation procedure

- Liquibase 3.x requires Java
- Install JDK, add JDK Path variable in System Variables
- Install the Mysql Connector for Java
- Download liquibase from  http://www.liquibase.org/download  and set path
- Test installation using command 

```
Liquibase –version
```

## Update command using liquibase

```
liquibase
--driver=com.mysql.jdbc.Driver
--classpath=mysql-connector-java-8.0.19.jar
--changeLogFile=db.changelog-1.0.xml
--url="jdbc:mysql://localhost:3306/liquibasetraining?autoReconnect=true&useSSL=false”
--username=root
--password=12345678
update
```

## Liquibase properties

- Instead of specifying command line parameters each time you run Liquibase, you can keep them in a Java properties file named liquibase.properties in the same directory.
- Then you can just run liquibase <command>.The properties file would look like this. Here we are using master.xml which has the changelog file db.changelog-1.0.xml.

```
changeLogFile=master.xml
driver=com.mysql.jdbc.Driver url=jdbc:mysql://localhost:3306/liquibasetraining?autoReconnect=true&amp;useSSL=false username=root
password=12345678
```

## Liquibase command using property file:

```
liquibase 
--defaultsFile=liquibase.properties 
--classpath=mysql-connector-java-8.0.19.jar
update
```

## Master.xml 

```
<?xml version="1.0" encoding="UTF-8"?> 
<databaseChangeLog xmlns="http://www.liquibase.org/xml/ns/dbchangelog" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.1.xsd">
 <changeSet id="1" author=”abc"> 
<createTable tableName="order"> 
<column name="id" type="int"> 
	<constraints primaryKey="true" nullable="false"/>
</column>
<column name="Ordername" type="varchar(50)"/>
<column name="Qty" type="int"> </column>
 </createTable>
 </changeSet>
 <changeSet id="tag-1.0" author=”abc"> 
<tagDatabase tag="1.0" />
 </changeSet> 
</databaseChangeLog>

```

## Running Liquibase using Maven

- Liquibase can be controlled via a Maven plug-in which can be obtained from the central Maven repository. 
- It gives advantages like the control over dependencies, running tests, plugins, versioning your software, etc.

### For Running Liquibase using Maven:

- Create a maven project
- Add the Liquibase Plugin to your POM 
- Configuration of the plugin is done via the Plugin section of the pom.xml
- Add a liquibase property file to the project, this should point to the corresponding database and its username and password
- Below is the dependency
```
<dependency> 
<groupId>org.liquibase</groupId>
 <artifactId>liquibase-core</artifactId> 
<version>3.5.3</version> 
</dependency>
```

- Below is the property file
```
changeLogFile=master_1.0.xml
driver=com.mysql.jdbc.Driver url=jdbc:mysql://localhost:3306/mavenblogproject?autoReconnect=true&amp;useSSL=false username=root
password=password123
```


- Add corresponding changelog file with name “master_1.0.xml” 

## Liquibase plugin

```
<plugin> 
<groupId>org.liquibase</groupId> 
<artifactId>liquibase-maven-plugin</artifactId>
<version>3.5.3</version>
<configuration> 
	<propertyFile>liquibase.properties location</propertyFile>
</configuration> 
<executions>
<execution> 
<goals> 
	<goal>update</goal> 
</goals>
</execution> 
</executions>
 </plugin>

```

### Maven command

Now, you can execute liquibase using Maven command. 

```
mvn liquibase:update
```
