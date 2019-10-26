#!/bin/bash
echo "Updating System"
sudo apt-get update -y
echo "Installing APACHE"
sudo apt-get install apache2 -y
echo "Starting Apache"
sudo systemctl start apache2.service
echo "Install MYSQL - Follow sets to setup MYSQL"
sudo apt-get install mysql-server -y
sudo /usr/bin/mysql_secure_installation
echo "Enabling APACHE and MYSQL Service"
sudo systemctl enable apache2.service
sudo systemctl enable mysql.service
sudo a2enmod rewrite
sudo a2dismod rewrite
sudo systemctl restart apache2.service
echo "Starting MySQL Server"
sudo systemctl start mysqld.service
echo "Installing GIT"
apt-get install git-core
echo "Input username"
read varname
git config --global user.name varname
echo "Input email"
read varemail
git config --global user.email varname
cd /var/www/html/
echo "CLONING GIT"
git clone https://github.com/FluffyFatBunny/ICT302_RegNQuiz.git
cd /ICT302_RegNQuiz
mvn clean package
sudo useradd regnquiz
sudo passwd regnquiz
sudo chown regnquiz:regnquiz REG_N_QUIZ-1-REGNQUIZ.jar
sudo ln -s /target/REG_N_QUIZ-1-REGNQUIZ.jar /etc/init.d/REGNQUIZ
sudo service REGNQUIZ start
echo "REGNGO has been installed and setup as a service called REGNQUIZ"
$SHELL
