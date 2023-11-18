# Author: Joseph Schwalb
# Class: CS524
# Professor: Delugach
# Date: 11/14/23
# Programming Assignment 1
#
# This program looks for an input file "register.txt" in the current working directory that this file exists in. This
# program expects the input file to have student names and ids, course names and ids, and a list of student ids
# enrolled in a class (id).
#
# This program is designed using two classes - student and class. A student has a name, an id, and a list of courses
# (courses_enrolled) that they're enrolled in. A class has a course record number (crn), a course name (cname), and
# seats which are occupied by students.
#
# The input file is defined by the homework assignment, but I break it down into three states here.
# State 1 - Collect Student information
# State 2 - Collect Course Information
# State 3 - Enroll students in courses
#
# The output is generated after each class is filled with the students assigned to them. We do not expect every student
# to be enrolled in classes and do not expect every class to have students. In the event a course is found during
# enrollment that was not found while collecting course information, I note this error in the output and continue. In
# the event of a student id that was not previously found while collecting student information, I note this error in the
# output and continue.
#
# ~ My Impressions of Ruby ~
# It was easy to pick up, feels vaguely similar to python. I was not aware of the conventions we discussed as part of
# Quiz K because I did not need to employ them to finish this assignment, but oh well. I did not like that I needed to
# create a "get" method to find the value of each attribute, seems unnecessary to me (a python developer who does not
# care much about private/public conventions). I would've liked to reference objects as dictionary objects, I think that
# have made code a bit neater. Ruby feels like a mixture of java and python.
########################################################################################################################

class Student
  # This class represents a student.
  def initialize(id, name)
    # Each student has an ID, a name, and a list of courses they are enrolled in.
    @id = id
    @name = name
    @courses_enrolled = {}

  end

  def id
    # returns the id of this object
    @id
  end

  def name
    # returns the name of this object
    @name
  end

  def courses_enrolled
    # returns the courses_enrolled of this object
    @courses_enrolled
  end

  def enroll(crn, cname)
    # enrolls a student in a course
    @courses_enrolled[crn] ||= cname
  end

  def drop(crn, cname)
    # drops a student in a course
    @courses_enrolled.delete(crn)
  end

end

class Class
  # This class represents a Class.
  def initialize(crn, cname)
    # Each class has a CRN (Class Identifier), a Class name, and seats that students can sit in.
    @crn = crn
    @cname = cname
    @seats = {}

  end

  def crn
    # returns the crn of this object
    @crn
  end

  def cname
    # returns the cname of this object
    @cname
  end

  def seats
    # returns the seats of this object
    @seats
  end

  def add_student(student)
    # Enroll student in course and assign them a seat.
    student.enroll(@crn, @cname)
    @seats[student.id] ||= student.name
  end

  def drop_student(student)
    # Drop student in course and remove their seat.
    # Warns if student is not found in class.
    flag = false
    seats.each do |student_tmp|
      if student.id == student_tmp.id
        student.drop(@crn, @cname)
        @seats.delete(student.id)
        flag = true
      end
    end

    if flag == false
      puts "Student #{student.name} (#{student.id} not enrolled in class."
    end

  end

end

def read_file(file_path)
  # Reads from input file, extracts students and class information.
  # returns object that has both students and classes found in the input file.
  classes = {}
  students = {}
  objects_to_return = []

  state = 1

  File.foreach(file_path) do |line|
    tokens = line.split

    if tokens.length == 0
      state += 1

    elsif state == 1
      student_id = tokens.shift
      student_name = tokens
      student = Student.new(student_id, tokens)
      students[student.id] ||= student

    elsif state == 2
      crn = tokens.shift
      cname = tokens
      cls = Class.new(crn, cname)
      classes[cls.crn] = cls

    elsif state == 3
      student_id = tokens.shift
      crn = tokens.shift

      begin
        classes[crn].add_student(students[student_id])
      rescue => e
        puts "Course #{crn} does not exist."
      end

      flag = false
      students.each do |id|
        if student_id == id[0]
          flag = true
        end
      end

      if flag == false
        puts "WARNING: Student ID #{student_id} does not exist."
      end

    end

  end

  objects_to_return.push(students, classes)
  objects_to_return
end

def main()
  # Main function that drives this program.
  # Finds "register.txt" from local directory and passes this path as an argument to the read_file function.
  # Prints information about the students and the courses found in register.txt

  file_path = File.join(File.dirname(__FILE__), 'register.txt')
  objects = read_file(file_path)
  students = objects[0]
  classes = objects[1]

  # Section 1
  puts "#######################################################"
  puts "####################### Part 1 ########################"
  puts "#######################################################"
  puts ""

  students.each do |student_id, student|
    student.courses_enrolled.each do |crn, cname|
      puts "#{student.name.join(" ")} is enrolled in #{cname.join(" ")}"
    end
    puts ""
  end

  puts "#######################################################"
  puts "#######################################################"
  puts "#######################################################"
  puts ""

  puts "#######################################################"
  puts "####################### Part 2 ########################"
  puts "#######################################################"
  puts ""

  classes.each do |crn, class_|
    seat_num = 0
    class_.seats.each do |student_id, student_name|
      seat_num += 1
      puts "#{class_.cname.join(" ")} - Seat #{seat_num} (#{student_name.join(" ")})"
    end
    puts "CRN #{crn} (#{class_.cname.join(" ")}) has #{class_.seats.length} students enrolled."
    puts ""
  end

  puts "#######################################################"
  puts "#######################################################"
  puts "#######################################################"
  puts ""
end

main
