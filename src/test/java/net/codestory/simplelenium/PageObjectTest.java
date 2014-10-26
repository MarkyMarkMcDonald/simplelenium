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

import static org.assertj.core.api.Assertions.assertThat;

public class PageObjectTest {
  @Test
  public void create_page() {
    ThePage thePage = PageObjectSection.create(ThePage.class);

    assertThat(thePage.h1).isNotNull();
    assertThat(thePage.h4).isNotNull();
    assertThat(thePage.name).isNotNull();
    assertThat(thePage.age).isNotNull();
  }

  @Test
  public void inject_elements() {
    ThePage thePage = new ThePage();

    PageObjectSection.injectMissingElements(thePage);

    assertThat(thePage.h1).isNotNull();
    assertThat(thePage.h4).isNotNull();
    assertThat(thePage.name).isNotNull();
    assertThat(thePage.age).isNotNull();
    assertThat(thePage.privateElement).isNotNull();
  }

  @Test
  public void inject_page_objects() {
    TheTest theTest = new TheTest();

    PageObjectSection.injectMissingPageObjects(theTest);

    assertThat(theTest.page).isNotNull();
    assertThat(theTest.anotherPage).isNotNull();
    assertThat(theTest.privatePage).isNotNull();
  }

  @Test
  public void inject_sections() {
    ThePage thePage = new ThePage();

    PageObjectSection.injectMissingElements(thePage);

    assertThat(thePage.section).isNotNull();
    assertThat(thePage.section.name).isNotNull();
    assertThat(thePage.section.age).isNotNull();
    assertThat(thePage.section.subSection).isNotNull();
    assertThat(thePage.section.subSection.name).isNotNull();
  }

  private static class TheTest {
    ThePage page;
    final ThePage anotherPage = new ThePage();
    private ThePage privatePage;
  }

  private static class ThePage implements PageObject {
    final DomElement h1 = find("h1");
    final DomElement h4 = find("h4");
    DomElement name;
    final DomElement age = find(".age");
    private DomElement privateElement;
    Section section;

    @Override
    public String url() {
      return "/";
    }
  }

  private static class Section implements PageObjectSection {
    DomElement name;
    DomElement age = find(".age");
    SubSection subSection;
  }

  private static class SubSection implements PageObjectSection {
    DomElement name;
  }
}
