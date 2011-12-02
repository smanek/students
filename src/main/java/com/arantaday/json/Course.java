package com.arantaday.json;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.IOException;

/**
* Created by IntelliJ IDEA.
* User: smanek
* Date: 12/1/11
* Time: 11:55 AM
* To change this template use File | Settings | File Templates.
*/
@JsonSerialize(using = Course.CourseSerializer.class)
@JsonDeserialize(using = Course.CourseDeserializer.class)
public class Course {

  private final Department department;
  private final long courseNumber;

  public Course(Department department, long courseNumber) {
    assert department != null;
    this.department = department;
    this.courseNumber = courseNumber;
  }

  public long getCourseNumber() {
    return courseNumber;
  }

  public Department getDepartment() {
    return department;
  }

  @Override
  public String toString() {
    return "Course{" +
        "department=" + department +
        ", courseNumber=" + courseNumber +
        '}';
  }

    public static class CourseSerializer extends JsonSerializer<Course> {
    @Override
    public void serialize(Course course, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
        throws IOException, JsonProcessingException {
      jsonGenerator.writeString(course.getDepartment().getShortName() + "-" + course.getCourseNumber());
    }
  }

  public static class CourseDeserializer extends JsonDeserializer<Course> {
    @Override
    public Course deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
        throws IOException, JsonProcessingException {
      final String courseParts[] = jsonParser.getText().split("-", 2);
      assert courseParts.length == 2;

      final Department department = Department.fromShortName(courseParts[0]);
      assert department != null;

      final long courseNumber = Long.parseLong(courseParts[1]);

      return new Course(department, courseNumber);
    }
  }
}
