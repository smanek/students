package com.arantaday.json;

import com.google.common.collect.Lists;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Student {

  private static final AtomicInteger UID_COUNTER = new AtomicInteger(0);

  private final int uid;
  private final String name;
  private final Date dateOfBirth;
  private final boolean enrolled;
  private final List<Course> courses;

  public Student(String name, Date dateOfBirth, boolean enrolled, Iterable<Course> courses) {
    this(UID_COUNTER.incrementAndGet(), name, dateOfBirth, enrolled, courses);
  }

  @JsonCreator
  public Student(@JsonProperty("uid") int uid,
                 @JsonProperty("name") String name,
                 @JsonProperty("dateOfBirth") Date dateOfBirth,
                 @JsonProperty("enrolled") boolean enrolled,
                 @JsonProperty("courses") Iterable<Course> courses) {
    this.uid = uid;
    this.name = name;
    this.dateOfBirth = dateOfBirth;
    this.enrolled = enrolled;
    this.courses = Collections.unmodifiableList(Lists.newArrayList(courses));
  }

  public int getUid() {
    return uid;
  }

  public String getName() {
    return name;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public boolean isEnrolled() {
    return enrolled;
  }

  public List<Course> getCourses() {
    return courses;
  }

  @Override
  public String toString() {
    return "Student{" +
        "name='" + name + '\'' +
        ", dateOfBirth=" + dateOfBirth +
        ", enrolled=" + enrolled +
        ", courses=" + courses +
        '}';
  }
}
