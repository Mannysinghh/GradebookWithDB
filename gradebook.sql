select * from gradebook.studentGrade;

select * from gradeWeight;

SET SQL_SAFE_UPDATES = 0;
update gradeWeight
set examWeight = 31 , hwWeight = 14, quizWeight = 15, finalWeight = 40;

update examGrade set examGrade = 90
where s_id = '00128';




USE gradebook;
DROP TABLE IF EXISTS `quizGrade`;
   create table quizGrade (
  s_id varchar(5) not null,
  quiz varchar(20),
  quizGrade integer,
    primary key (s_id)
  );

alter table finalGrade
change finalGrade final integer;


 create table finalGrade (
  s_id varchar(5) not null,
final varchar(20),
  finalGrade integer,
    primary key (s_id)
  );
  create table hwGrade (
  s_id varchar(5) not null,
  homwork varchar(20),
  homeworkGrade integer,
    primary key (s_id)
  );
  
  create table examGrade (
  s_id varchar(5) not null,
exam varchar(20),
  examGrade integer,
    primary key (s_id)
  );
  

INSERT hwGrade VALUES
('00128',"hw1",80),
('12345',"hw1",60),
('19991',"hw1",40),
('23121',"hw1",75),
('37324',"hw1",86),
('44553',"hw1",55);

INSERT examGrade VALUES
('00128',"exam1",80),
('12345',"exam1",60),
('19991',"exam1",40),
('23121',"exam1",75),
('37324',"exam1",86),
('44553',"exam1",55);

INSERT quizGrade VALUES
('00128',"quiz1",80),
('12345',"quiz1",60),
('19991',"quiz1",40),
('23121',"quiz1",75),
('37324',"quiz1",86),
('44553',"quiz1",55);

INSERT finalGrade VALUES
('00128',"final",80),
('12345',"final",60),
('19991',"final",40),
('23121',"final",75),
('37324',"final",86),
('44553',"final",55);

