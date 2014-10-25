/**
 * Copyright (C) 2013-2014 all@code-story.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */
package net.codestory.simplelenium;

import org.junit.Test;

import java.util.regex.Pattern;

public class ListTest extends AbstractTest {
  @Test
  public void list() {
    goTo("/list");

    find(".name").should().contain("Bob Morane", "Joe l'Indien");
    find(".name").should().beDisplayed();
    find(".name").should().haveSize(2);
    find(".name").should().haveLessItemsThan(3);
    find(".name").should().haveMoreItemsThan(1);
    find(".name").should().not().beEmpty();
    find(".name").should().not().contain("Casper", "Zorro");
    find(".name").should().match(Pattern.compile("([a-zA-Z ]+)"));
  }

  @Test
  public void list_with_verification_chaining() {
    goTo("/list");

    find(".name").should()
      .contain("Bob Morane", "Joe l'Indien")
      .beDisplayed()
      .haveSize(2)
      .haveLessItemsThan(3)
      .haveMoreItemsThan(1)
      .not().beEmpty()
      .not().contain("Casper", "Zorro")
      .match(Pattern.compile("([a-zA-Z ]+)"));
  }

  @Test
  public void filter_with_text() {
    goTo("/list");

    find(".name").withText("Bob Morane").should().haveSize(1);
    find(".name").withText("Bob").should().haveSize(1);
    find(".name").withText("Bob Morane").should().exist();
    find(".name").withText("John Doe").should().not().exist();
    find(".name").withText().equalsTo("Bob Morane").should().haveSize(1);
    find(".name").withText().startsWith("B").should().haveSize(1);
    find(".name").withText().endsWith("Morane").should().haveSize(1);
    find(".name").withText().contains("ora").should().haveSize(1);
    find(".name").withText().matches(Pattern.compile("[0-9]*")).should().beEmpty();
    find(".name").withText().matches(Pattern.compile("[a-zA-Z ']*")).should().haveSize(2);
  }

  @Test
  public void filter_with_id() {
    goTo("/list");

    find(".name").withId("joe").should().haveSize(1);
    find(".name").withId().equalsTo("joe").should().haveSize(1);
    find(".name").withId().equalsTo("joe").withText("Joe").should().haveSize(1);
    find(".name").withId().equalsTo("joe").withText("Bob").should().beEmpty();
  }

  @Test
  public void filter_with_name() {
    goTo("/list");

    find(".name").withName("theJoe").should().haveSize(1);
  }
}
