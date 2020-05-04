IMAGE_NAME = "generic/debian10"
J = 1

Vagrant.configure("2") do |config|

	config.vm.define "jenkins" do |master|
		master.vm.provider "virtualbox" do |v|
			v.name = "jenkins"
			v.memory = 2048
			v.cpus = 2
		end
		master.vm.box = IMAGE_NAME
		master.vm.network "private_network", ip: "10.50.10.10"
		master.vm.hostname = "jenkins"
		master.vm.synced_folder ".", "/vagrant", type: "virtualbox"
		master.vm.provision :shell, path: "./files/user.sh", args: "appuser"
		master.vm.provision :shell, path: "./files/vm-route.sh", privileged: true
		master.vm.provision :shell, path: "./files/install.sh"
		master.vm.provision :shell, privileged: true, inline: <<-SHELL
				mkdir -p /var/lib/jenkins/init.groovy.d
				cp /vagrant/files/conf/* /var/lib/jenkins/init.groovy.d/
				chown jenkins:jenkins /var/lib/jenkins/init.groovy.d/*
				service jenkins force-reload
				cp /home/appuser/.ssh/appuser /var/lib/jenkins/secrets/
				chown jenkins:jenkins /var/lib/jenkins/secrets/appuser
		SHELL
	end

	(1..J).each do |i|
		config.vm.define "node#{i}" do |slave|
		slave.vm.provider "virtualbox" do |v|
			v.name = "node#{i}"
			v.memory = 1024
			v.cpus = 1
		end
			slave.vm.box = IMAGE_NAME
			slave.vm.network "private_network", ip: "10.50.10.#{i + 19}"
			slave.vm.hostname = "node#{i}"
			slave.vm.synced_folder ".", "/vagrant", type: "virtualbox"
			slave.vm.provision :shell, path: "./files/user-slave.sh", args: "appuser"
			slave.vm.provision :shell, path: "./files/vm-route.sh", privileged: true
			slave.vm.provision :shell, path: "./files/java.sh", privileged: true
			slave.vm.provision :shell, inline: <<-SHELL
				sudo apt update && sudo apt install -y npm gulp
			SHELL
		end
	end
end