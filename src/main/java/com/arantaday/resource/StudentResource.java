package com.arantaday.resource;

import com.arantaday.json.Course;
import com.arantaday.json.Student;
import com.arantaday.parameter.CsvParam;
import com.arantaday.parameter.DateParam;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Path("/students")
@Produces({MediaType.APPLICATION_JSON})
public class StudentResource {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  // We'll just 'persist' data in memory for this test application
  private static final Map<Integer, Student> STUDENTS = Maps.newConcurrentMap();

  @GET
  public Collection<Integer> listStudentIds() {
    return STUDENTS.keySet();
  }

  @GET
  @Path("{uid: [0-9]+}")
  public Student getStudent(@PathParam("uid") int uid) {
    final Student student = STUDENTS.get(uid);

    // return a 404 if no user with the given UID exists
    if (student == null) {
      throw new WebApplicationException(Response.Status.NOT_FOUND);
    } else {
      return student;
    }
  }

  @POST
  @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
  public int createStudent(@FormParam("name") String name,
                           @FormParam("dob") DateParam dateParam,
                           @FormParam("enrolled") @DefaultValue("true") boolean enrolled,
                           @FormParam("courses") @DefaultValue("") CsvParam csvCourses) throws IOException {
    assert name != null && name.length() > 2 && name.length() < 100;
    assert dateParam != null && dateParam.getValue() != null;

    List<Course> courses = Lists.newLinkedList();
    for (String courseStr : csvCourses.getValue()) {
      courses.add(MAPPER.readValue("\"" + courseStr + "\"", Course.class));
    }

    final Student newStudent = new Student(name, dateParam.getValue(), enrolled, courses);
    final Student oldStudent = STUDENTS.put(newStudent.getUid(), newStudent);
    assert oldStudent == null;

    return newStudent.getUid();
  }

  @DELETE
  @Path("{uid: [0-9]+}")
  public boolean deleteStudent(@PathParam("uid") int uid) {
    final Student deletedStudent = STUDENTS.remove(uid);

    if (deletedStudent == null) {
      throw new WebApplicationException(Response.Status.NOT_FOUND);
    } else {
      return true;
    }
  }
}
