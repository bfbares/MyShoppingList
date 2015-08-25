/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
DROP DATABASE IF EXISTS `msl_dev`;
CREATE SCHEMA `msl_dev` DEFAULT CHARACTER SET utf8 ;
GRANT ALL ON msl_dev.* TO 'msluser'@'%' IDENTIFIED BY 'mslpass123';
GRANT ALL ON msl_dev.* TO 'msluser'@'localhost' IDENTIFIED BY 'mslpass123';
use msl_dev;
source dbmsl-schema.sql
source dbmsl-dev-data.sql
source dbmsl-updates.sql
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;