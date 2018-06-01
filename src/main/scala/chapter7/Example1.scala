package chapter7

import cats.syntax.foldable._
import cats.instances.all._


object Example1 extends App {

  // group students on the basis of class id and define total number of students using scala libraries
  case class Students(name: String, classID: String)

  val students = List(Students("james1", "1"), Students("james2", "1"),
    Students("micky1", "1"), Students("micky2", "2"))

  val noOfStudentsEachClassStudents = students.groupBy(student => student.classID).map {
    case (id, stdnts) => (id, (stdnts.size, stdnts))
  }

  // group students on the basis of class id and define total number of students using scala-cats
  val noOfStudentsEachClassStudentsCats = students.foldMap(st => Map(st.classID -> (1, List(st))))

  assert(noOfStudentsEachClassStudents == noOfStudentsEachClassStudentsCats)
}
