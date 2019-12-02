-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 12, 2019 at 08:41 PM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 5.6.40

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `projectdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `courses`
--

CREATE TABLE `courses` (
  `courseCode` varchar(20) NOT NULL,
  `courseName` varchar(100) NOT NULL,
  `credit` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `courses`
--

INSERT INTO `courses` (`courseCode`, `courseName`, `credit`) VALUES
('CSE4000', 'Research Methodology ', 3),
('CSE4047', 'Advance JAVA', 3),
('CSE4048', 'Advance JAVA Lab', 1);

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `employeeID` varchar(13) NOT NULL,
  `employeeName` varchar(60) NOT NULL,
  `department` varchar(10) NOT NULL,
  `designation` varchar(30) NOT NULL,
  `sex` varchar(6) NOT NULL,
  `contract` varchar(15) NOT NULL,
  `address` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`employeeID`, `employeeName`, `department`, `designation`, `sex`, `contract`, `address`) VALUES
('ar', 'Mr. Md. Ashiqur Rahman ', 'CSE', 'teacher', 'Male', '', 'Rampura,Dhaka'),
('kmh', 'Monirul Hasan Tomal', 'CSE', 'teacher', 'Male', '', 'Banani,Dhaka'),
('raj', 'Roksana Akter Jolly', 'CSE', 'teacher', 'Female', '', 'Rampura, Dhaka'),
('sm', 'Shahariar Monzoor', 'CSE', 'chairman', 'Male', '', 'Banani, Dhaka');

-- --------------------------------------------------------

--
-- Table structure for table `notice`
--

CREATE TABLE `notice` (
  `noticeName` varchar(255) NOT NULL,
  `lastDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `notice`
--

INSERT INTO `notice` (`noticeName`, `lastDate`) VALUES
('Registration', '2019-06-12');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `studentID` varchar(13) NOT NULL,
  `studentName` varchar(60) NOT NULL,
  `department` varchar(10) NOT NULL,
  `batch` int(3) NOT NULL,
  `sex` varchar(6) NOT NULL,
  `contract` varchar(15) NOT NULL,
  `address` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`studentID`, `studentName`, `department`, `batch`, `sex`, `contract`, `address`) VALUES
('2016000000008', 'Md. Shohel Rana', 'CSE', 42, 'Male', '+8801642-090564', 'Mohammodpur, Dhaka'),
('2016000000009', 'Md. Rakibul Hasan', 'CSE', 42, 'Male', '+8801642-090363', 'North Kafrul , Dhaka Cantonment, Dhaka - 1206'),
('2016000000010', 'Jafree Mondol Deep ', 'CSE', 42, 'Male', '+8801777-687972', 'Uttara , Dhaka'),
('2016000000011', 'Irin Akter', 'CSE', 42, 'Female', '+8801555-090363', 'Savar, Dhaka'),
('2016000000012', 'Nahida Sultana Nishi', 'CSE', 42, 'Female', '+8801777-090363', 'Cantonment, Dhaka'),
('2016000000024', 'Mahamudul Hasan Farhan', 'CSE', 42, 'Male', '+8801756-090363', 'Airport, Dhaka');

-- --------------------------------------------------------

--
-- Table structure for table `student_limit`
--

CREATE TABLE `student_limit` (
  `employeeID` varchar(13) NOT NULL,
  `studentLimit` int(3) NOT NULL DEFAULT '7'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student_limit`
--

INSERT INTO `student_limit` (`employeeID`, `studentLimit`) VALUES
('ar', 3),
('kmh', 4),
('raj', 3);

-- --------------------------------------------------------

--
-- Table structure for table `student_registration`
--

CREATE TABLE `student_registration` (
  `studentID` varchar(13) NOT NULL,
  `courseCode` varchar(50) NOT NULL,
  `faculty` varchar(60) DEFAULT NULL,
  `topic` varchar(100) DEFAULT NULL,
  `groupname` varchar(20) DEFAULT NULL,
  `status` varchar(30) NOT NULL DEFAULT '',
  `permission` varchar(10) NOT NULL DEFAULT 'NO'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student_registration`
--

INSERT INTO `student_registration` (`studentID`, `courseCode`, `faculty`, `topic`, `groupname`, `status`, `permission`) VALUES
('2016000000008', 'CSE4000 ', 'Mr. Md. Ashiqur Rahman', 'Algorithm', 'ALPHA', '', 'NO'),
('2016000000009', 'CSE4000 ', 'Monirul Hasan Tomal', 'Advance Java', 'RISK', '', 'NO'),
('2016000000010', 'CSE4000 ', 'Monirul Hasan Tomal', 'Advance Java', 'RISK', '', 'NO'),
('2016000000011', 'CSE4000 ', 'Monirul Hasan Tomal', 'Graphics', 'ROAR', '', 'NO'),
('2016000000012', 'CSE4000 ', 'Mr. Md. Ashiqur Rahman', 'Algorithm', 'ALPHA', '', 'NO'),
('2016000000024', 'CSE4000 ', 'Monirul Hasan Tomal', 'Graphics', 'ROAR', '', 'NO');

-- --------------------------------------------------------

--
-- Table structure for table `user_login`
--

CREATE TABLE `user_login` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `designation` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_login`
--

INSERT INTO `user_login` (`username`, `password`, `designation`) VALUES
('2016000000008', '22222', 'student'),
('2016000000009', '12345', 'student'),
('2016000000010', '11111', 'student'),
('2016000000011', '44444', 'student'),
('2016000000012', '55555', 'student'),
('2016000000024', '33333', 'student'),
('ar', 'ashiquesir', 'teacher'),
('kmh', 'tomalboss', 'teacher'),
('raj', 'jollymam', 'teacher'),
('sm', '', 'chairman');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `courses`
--
ALTER TABLE `courses`
  ADD PRIMARY KEY (`courseCode`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`employeeID`);

--
-- Indexes for table `notice`
--
ALTER TABLE `notice`
  ADD PRIMARY KEY (`noticeName`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`studentID`);

--
-- Indexes for table `student_limit`
--
ALTER TABLE `student_limit`
  ADD PRIMARY KEY (`employeeID`);

--
-- Indexes for table `student_registration`
--
ALTER TABLE `student_registration`
  ADD PRIMARY KEY (`studentID`,`courseCode`);

--
-- Indexes for table `user_login`
--
ALTER TABLE `user_login`
  ADD PRIMARY KEY (`username`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
