mutable struct Student
    name::String   
    id::String
    courses_enrolled::Dict{String, String}
   
    function Student(name::String, id::String, courses_enrolled::Dict{String, String})
        obj = new(name, id, courses_enrolled)
        return obj
    end
end

mutable struct Classroom   
    cname::String   
    crn::String
    seats::Dict{String, Student}

    function Classroom(cname::String, crn::String, seats::Dict{String, Student})
        obj = new(cname, crn, seats)
        return obj
    end
end

function add_student(classroom::Classroom, student::Student)
# Enrolls a student in a course
    #println("$(classroom.seats)")
    if haskey(classroom.seats, student.id)
        println("$(student.name) already enrolled.")
    else
        classroom.seats[student.id] = student
        enroll(classroom, student)
        #student.courses_enrolled[crn] = classroom.cname
    end   
end

function drop_student(classroom::Classroom, student::Student)
    # Drop a student in a course
    if haskey(classroom.seats, student.id)
        drop_student(classroom, student)
        println("$(student.name) dropped.")
    else
        println("Student not enrolled in $(classroom.cname) ($(classroom.crn))")
    end
        
end

function enroll(classroom::Classroom, student::Student)
    # Enrolls a student in a course
    if haskey(student.courses_enrolled, classroom.crn)
        println("Student already enrolled in $(classroom.cname) ($(classroom.crn))")
    else
        #add_student(classroom, student)
        student.courses_enrolled[classroom.crn] = classroom.cname
        println("Successfully enrolled $(student.name)")

    end
end

function drop(classroom::Classroom, student::Student)
    # Drops a student in the course 
    if haskey(student.courses_enrolled, classroom.crn)
        delete!(student.courses_enrolled, classroom.crn)
    else
        println("Student not enrolled in $(classroom.cname) ($(crn)).")
    end
end

function read_file(file_path::String)
    # Reads from input file, extracts students and class information.
    # returns object that has both students and classes found in the input file.
    classes = Dict{String, Classroom}()
    students = Dict{String, Student}()
    
    state = 1

    file = open(file_path, "r")

    # Iterate over each line in the file
    for line in eachline(file)
        # Tokenize the line using a space as the delimiter
        tokens = split(line)

        if length(tokens) > 0

            if state == 1
                student_id = string(tokens[1])
                student_name = join(tokens[2:end], " ")
                new_student = Student(student_name, student_id, Dict{String, String}())
                
                if haskey(students, student_id)
                    println("Duplicate Student found. Errors may occur.")
                end
                students[student_id] = new_student

            elseif state == 2
                crn = string(tokens[1])
                cname = join(tokens[2:end], " ")
                new_classroom = Classroom(cname, crn, Dict{String, Student}())
                
                if haskey(classes, crn)
                    println("Duplicate Class found. Errors may occur.")
                else
                    classes[crn] = new_classroom
                end
                    
            elseif state == 3
                student_id = string(tokens[1])
                crn = string(tokens[2])

                try
                    add_student(classes[crn], students[student_id])
                catch e
                    println("Exception $e.")
                    if !haskey(classes, crn)
                        println("CRN $(crn) does not exist.")
                    elseif !haskey(students, student_id)
                        println("Student ($student_id) does not exist.")
                    end
                end
            end
        else
            state = state + 1
        end
    end

    close(file)

    return students, classes
end

file_path = "./register.txt"
students, classes = read_file(file_path)


println("#######################################################")
println("####################### Part 1 ########################")
println("#######################################################")

for (id, student) in students
    for (crn, cls) in student.courses_enrolled
       println("$(student.name) is enrolled in $(cls)") 
    end
    println()
end

println("#######################################################")
println("#######################################################")
println("#######################################################")
println()
println("#######################################################")
println("####################### Part 2 ########################")
println("#######################################################")

for (crn, classroom) in classes
    i = 1
    for (crn, student) in classroom.seats
       println("$(classroom.cname): Seat $i - $(student.name).")
       i = i + 1
    end
    println()
end

println("#######################################################")
println("#######################################################")
println("#######################################################")
println()


#   # Section 1
#   puts "#######################################################"
#   puts "####################### Part 1 ########################"
#   puts "#######################################################"
#   puts ""

#   students.each do |student_id, student|
#     student.courses_enrolled.each do |crn, cname|
#       puts "#{student.name.join(" ")} is enrolled in #{cname.join(" ")}"
#     end
#     puts ""
#   end

#   puts "#######################################################"
#   puts "#######################################################"
#   puts "#######################################################"
#   puts ""

#   puts "#######################################################"
#   puts "####################### Part 2 ########################"
#   puts "#######################################################"
#   puts ""

#   classes.each do |crn, class_|
#     seat_num = 0
#     class_.seats.each do |student_id, student_name|
#       seat_num += 1
#       puts "#{class_.cname.join(" ")} - Seat #{seat_num} (#{student_name.join(" ")})"
#     end
#     puts "CRN #{crn} (#{class_.cname.join(" ")}) has #{class_.seats.length} students enrolled."
#     puts ""
#   end

#   puts "#######################################################"
#   puts "#######################################################"
#   puts "#######################################################"
#   puts ""
# end