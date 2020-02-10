# -*- mode: ruby -*-
# vi: set ft=ruby :

# All Vagrant configuration is done below. The "2" in Vagrant.configure
# configures the configuration version (we support older styles for
# backwards compatibility). Please don't change it unless you know what
# you're doing.
Vagrant.configure("2") do |config|
  # The most common configuration options are documented and commented below.
  # For a complete reference, please see the online documentation at
  # https://docs.vagrantup.com.

  # Every Vagrant development environment requires a box. You can search for
  # boxes at https://vagrantcloud.com/search.
  config.vm.box = "ubuntu/xenial64"
  config.vm.hostname = "helloworld"
  
  ENV['LC_ALL']="en_US.UTF-8"

  # Disable automatic box update checking. If you disable this, then
  # boxes will only be checked for updates when the user runs
  # `vagrant box outdated`. This is not recommended.
  # config.vm.box_check_update = false

  # Create a forwarded port mapping which allows access to a specific port
  # within the machine from a port on the host machine. In the example below,
  # accessing "localhost:8080" will access port 80 on the guest machine.
  # NOTE: This will enable public access to the opened port
  # config.vm.network "forwarded_port", guest: 80, host: 8080
  # config.vm.network "forwarded_port", guest: 8088, host: 8080

  # Create a forwarded port mapping which allows access to a specific port
  # within the machine from a port on the host machine and only allow access
  # via 127.0.0.1 to disable public access
  # config.vm.network "forwarded_port", guest: 8088, host: 8080, host_ip: "127.0.0.1"

  # Create a private network, which allows host-only access to the machine
  # using a specific IP.
  config.vm.network "private_network", ip: "192.168.33.14"

  # Create a public network, which generally matched to bridged network.
  # Bridged networks make the machine appear as another physical device on
  # your network.
  # config.vm.network "public_network"

  # Share an additional folder to the guest VM. The first argument is
  # the path on the host to the actual folder. The second argument is
  # the path on the guest to mount the folder. And the optional third
  # argument is a set of non-required options.
  # config.vm.synced_folder "../data", "/vagrant_data"

  # Provider-specific configuration so you can fine-tune various
  # backing providers for Vagrant. These expose provider-specific options.
  # Example for VirtualBox:
  #
   config.vm.provider "virtualbox" do |vb|
  #   # Display the VirtualBox GUI when booting the machine
   	  # vb.gui = true
  
     # Customize the amount of memory on the VM:
        vb.memory = "8192"
    	vb.cpus = 4
   end
  #
  # View the documentation for the provider you are using for more
  # information on available options.

  # Enable provisioning with a shell script. Additional provisioners such as
  # Puppet, Chef, Ansible, Salt, and Docker are also available. Please see the
  # documentation for more information about their specific syntax and use.	
  
  config.vm.provision "shell", inline: <<-SHELL
     apt-get update
     sudo apt install default-jre -y
     sudo apt-get install openjdk-8-jdk -y
     export DEBIAN_FRONTEND="noninteractive"
     sudo debconf-set-selections <<< "mysql-server mysql-server/root_password password 12345678"
     sudo debconf-set-selections <<< "mysql-server mysql-server/root_password_again password 12345678"
     sudo apt-get install -y mysql-server
     curl -o apache-tomcat-9.0.30.tar.gz https://www-eu.apache.org/dist/tomcat/tomcat-9/v9.0.30/bin/apache-tomcat-9.0.30.tar.gz
     tar -zxvf apache-tomcat-9.0.30.tar.gz
     rm apache-tomcat-9.0.30.tar.gz
     git clone https://github.com/venkateshwarant/MavenHelloWorld.git
     cd MavenHelloWorld
     sudo apt install maven -y
     sudo mvn clean install
     cp target/HelloWorld-0.0.1-SNAPSHOT.war ../apache-tomcat-9.0.30/webapps
     cd ../apache-tomcat-9.0.30/webapps
     mkdir helloworld
     cp HelloWorld-0.0.1-SNAPSHOT.war helloworld
     rm HelloWorld-0.0.1-SNAPSHOT.war
     cd helloworld
     sudo jar -xvf HelloWorld-0.0.1-SNAPSHOT.war
     sh ../../bin/startup.sh
     SHELL
end
