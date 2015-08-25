/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
DROP DATABASE IF EXISTS `msl_test`;
CREATE SCHEMA `msl_test` DEFAULT CHARACTER SET utf8 ;
GRANT ALL ON msl_test.* TO 'msluser'@'%' IDENTIFIED BY 'mslpass123';
GRANT ALL ON msl_test.* TO 'msluser'@'localhost' IDENTIFIED BY 'mslpass123';
use msl_test;
source dbmsl-schema.sql
source dbmsl-test-data.sql
source dbmsl-updates.sql
source dbmsl-procedures.sql
source dbmsl-views.sql
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;