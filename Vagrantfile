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
		master.vm.provision :shell, path: "./files/install.sh"
		master.vm.provision :shell, inline: <<-SHELL
				sudo mkdir -p /var/lib/jenkins/init.groovy.d
				sudo cp /vagrant/files/conf/00-install-plugins.groovy /var/lib/jenkins/init.groovy.d/
				sudo cp /vagrant/files/conf/01-global-settings.groovy /var/lib/jenkins/init.groovy.d/
				sudo chown jenkins:jenkins /var/lib/jenkins/init.groovy.d/*
				sudo service jenkins force-reload
		SHELL
	end

	# (1..J).each do |i|
		# config.vm.define "node#{i}" do |slave|
		# slave.vm.provider "virtualbox" do |v|
			# v.name = "node#{i}"
			# v.memory = 1024
			# v.cpus = 1
		# end
			# slave.vm.box = IMAGE_NAME
			# slave.vm.network "private_network", ip: "10.50.10.19+#{i}"
			# slave.vm.hostname = "node#{i}"
			# slave.vm.synced_folder ".", "/vagrant", type: "virtualbox"
			# slave.vm.provision :shell, path: "./files/user-slave.sh", args: "appuser"
			# slave.vm.provision :shell, inline: <<-SHELL
				# sudo apt update && sudo apt install -y default-jre default-jdk 
			# SHELL
		# end
	# end
end