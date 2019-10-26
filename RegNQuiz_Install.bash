#!/bin/bash
echo -e "\e[31mUpdating System\e[0m"
sudo apt-get update -y
echo -e "\e[31mInstalling APACHE\e[0m"
sudo apt-get install apache2 -y
echo -e "\e[31mStarting Apache\e[0m"
sudo systemctl start apache2.service
echo -e "\e[31mInstall MYSQL - Follow sets to setup MYSQL\e[0m"
sudo apt-get install mysql-server -y
sudo /usr/bin/mysql_secure_installation
echo -e "\e[31mLog into MYSQL with details entered before\e[0m"
echo -e "\e[31mFollow the GITHUB https://github.com/FluffyFatBunny/ICT302_RegNQuiz MYSQL install instructions to setup the database for RegNGo\e[0m"
echo -e "\e[31mEnter MYSQL username: \e[0m"
read username
mysql -u username -p
echo -e "\e[31mEnabling APACHE and MYSQL Service\e[0m"
sudo systemctl enable apache2.service
sudo systemctl enable mysql.service
sudo a2enmod rewrite
sudo a2dismod rewrite
sudo systemctl restart apache2.service
echo -e "\e[31mStarting MySQL Server\e[0m"
sudo systemctl start mysqld.service
echo -e "\e[31mInstalling GIT\e[0m"
sudo apt-get install git-core
echo -e "\e[31mInput username:\e[0m"
read varname
git config --global user.name varname
echo -e "\e[31mInput email:\e[0m"
read varemail
git config --global user.email varname
cd /var/www/html/
echo -e "\e[31mCLONING GIT\e[0m"
git clone https://github.com/FluffyFatBunny/ICT302_RegNQuiz.git
cd /ICT302_RegNQuiz
sudo apt-get install maven
mvn clean package
sudo useradd regnquiz
sudo passwd regnquiz
sudo chown regnquiz:regnquiz REG_N_QUIZ-1-REGNQUIZ.jar
sudo ln -s /target/REG_N_QUIZ-1-REGNQUIZ.jar /etc/init.d/REGNQUIZ
sudo service REGNQUIZ start
echo -e "\e[31mREGNGO has been installed and setup as a service called REGNQUIZ\e[0m"
$SHELL
