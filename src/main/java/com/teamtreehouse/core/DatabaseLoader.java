package com.teamtreehouse.core;

import com.teamtreehouse.course.Course;
import com.teamtreehouse.course.CourseRepository;
import com.teamtreehouse.review.Review;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class DatabaseLoader implements ApplicationRunner {
  private final CourseRepository courses;

  public DatabaseLoader(CourseRepository courses) {
    this.courses = courses;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    Course course = new Course("Java Basics", "http://house.com/java-basics");
    course.addReview(new Review(3, "You are LOOSER!!!"));
    courses.save(course);
    String[] templates = {
        "Up and running %s",
        "%s for beginners",
        "%s Basics"
    };
    String[] buzzwords = {
        "Java 9",
        "Delphi",
        ".NetFramework",
        "C#"
    };

    List<Course> bunchOfCourses = new ArrayList<>();
    IntStream.range(0, 100)
        .forEach(i -> {
          String template = templates[i % templates.length];
          String buzzword = buzzwords[i % buzzwords.length];
          String title = String.format(template, buzzword);
          Course c = new Course(title, "https://example.com");
          c.addReview(new Review(i%5, String.format("More %s please!!!", buzzword)));
          bunchOfCourses.add(c);
        });
    courses.save(bunchOfCourses);



  }
}
