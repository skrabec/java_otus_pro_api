package tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import dto.CoursesDto;
import dto.ScoreDto;
import dto.UserDto;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Test;
import services.CoursesApi;
import services.HttpHelper;
import java.util.List;

@Feature("HTTP tests")
public class HttpTests extends HttpHelper {

    private CoursesApi coursesApi = new CoursesApi();

    @Description("Get score test")
    @Test
    public void getScoreTest() {
        ScoreDto score = coursesApi.getScore(1)
            .extract()
            .as(ScoreDto.class);

        assertNotNull(score);
        assertEquals("Test user", score.getName());
        assertEquals(78, score.getScore());
    }

    @Description("Get users test")
    @Test
    public void getUsersTest() {
        List<UserDto> users = coursesApi.getUsers()
            .extract()
            .body()
            .jsonPath()
            .getList("", UserDto.class);


        assertThat(users, is(notNullValue()));
        assertThat(users, hasSize(1));

        UserDto firstUser = users.get(0);
        assertThat(firstUser.getName(), is("Test user"));
        assertThat(firstUser.getEmail(), is("test@test.test"));
        assertThat(firstUser.getCourse(), is("QA"));
        assertThat(firstUser.getAge(), is(23));

    }

    @Description("Get courses test")
    @Test
    public void getCourses() {
        List<CoursesDto> courses = coursesApi.getCourses()
            .extract()
            .body()
            .jsonPath()
            .getList("", CoursesDto.class);

        assertThat(courses, is(notNullValue()));
        assertThat(courses, hasSize(2));

        CoursesDto firstCourse = courses.get(0);
        assertThat(firstCourse.getName(), is("QA java"));
        assertThat(firstCourse.getPrice(), is(15000));

        CoursesDto secondCourse = courses.get(1);
        assertThat(secondCourse.getName(), is("Java"));
        assertThat(secondCourse.getPrice(), is(12000));

        assertThat(courses, contains(
            hasProperty("name", is("QA java")),
            hasProperty("name", is("Java"))
        ));
        assertThat(courses, contains(
            hasProperty("price", is(15000)),
            hasProperty("price", is(12000))
        ));
    }


}
