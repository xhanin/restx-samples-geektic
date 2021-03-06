package geeks;

import com.google.common.collect.Lists;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class GeekTest {
  @Test
  public void should_match_likes() {
    assertThat(geek("LIKE1", "", "").matches("LIKE1")).isTrue();
    assertThat(geek("", "LIKE2", "").matches("LIKE2")).isTrue();
    assertThat(geek("", "", "LIKE3").matches("LIKE3")).isTrue();
    assertThat(geek("LIKE", "LIKE", "LIKE").matches("LIKE")).isTrue();
  }

  @Test
  public void should_not_match_partials() {
    assertThat(geek("LIKE1", "", "").matches("LIKE")).isFalse();
  }

  @Test
  public void should_not_match_empty_string() {
    assertThat(geek("A", "B", "").matches("")).isFalse();
  }

  @Test
  public void should_ignore_case() {
    assertThat(geek("LIKE1", "", "").matches("like1")).isTrue();
  }

  @Test
  public void should_match_plurals() {
    assertThat(geek("cat", "", "").matches("cats")).isTrue();
    assertThat(geek("cats", "", "").matches("cat")).isTrue();
  }

  @Test
  public void should_ignore_leading_triling_spaces() {
    assertThat(geek(" cat ", "", "").matches(" cats ")).isTrue();
  }

  @Test
  public void should_compute_gravatar() {
    Geek geek = geek("LIKE1", "LIKE2", "LIKE3");
    geek.prenom = "PRENOM";
    geek.email = "david@gageot.net";
    geek.ville = "VILLE";

    Result result = geek.toResult();

    assertThat(result.prenom).isEqualTo("PRENOM");
    assertThat(result.like1).isEqualTo("LIKE1");
    assertThat(result.like2).isEqualTo("LIKE2");
    assertThat(result.like3).isEqualTo("LIKE3");
    assertThat(result.ville).isEqualTo("VILLE");
    assertThat(result.pictureUrl).isEqualTo("http://gravatar.com/avatar/f0887bf6175ba40dca795eb37883a8cf");
  }

    @Test
    public void should_use_pictureUrl_if_any() {
        Geek geek = geek("LIKE1", "LIKE2", "LIKE3");
      geek.prenom = "PRENOM";
      geek.email = "david@gageot.net";
      geek.ville = "VILLE";
      geek.pictureUrl = "http://img.io/me.jpg";

      Result result = geek.toResult();

      assertThat(result.prenom).isEqualTo("PRENOM");
      assertThat(result.like1).isEqualTo("LIKE1");
      assertThat(result.like2).isEqualTo("LIKE2");
      assertThat(result.like3).isEqualTo("LIKE3");
      assertThat(result.ville).isEqualTo("VILLE");
      assertThat(result.pictureUrl).isEqualTo("http://img.io/me.jpg");
    }

  private static Geek geek(String... likes) {
    Geek geek = new Geek();
    geek.likes = Lists.newArrayList(likes);
    return geek;
  }
}
