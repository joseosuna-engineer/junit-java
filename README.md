# JUnit5

<img src="https://github.com/joseosuna-engineer/junit-java/blob/main/junit5.png" align="center"  width="500" />

JUnit is a unit testing framework for the Java programming language. JUnit has been important in the development of test-driven development, and is one of a family of unit testing frameworks which is collectively known as xUnit that originated with SUnit.

```java
package com.prottonne;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

class MyJunit5Test {

    @BeforeEach
    public void configure() {
        // Code executed before the first test method
    }

    @Test
    public void testOneThing() {
        // Code that tests one thing
    }

    @Test
    public void testAnotherThing() {
        // Code that tests another thing
    }

    @Test
    public void testSomethingElse() {
        // Code that tests something else
    }

}

```

# Spring Boot

<img src="https://github.com/joseosuna-engineer/junit-java/blob/main/spring-boot.png" align="center"  width="300" />

**Spring** Boot makes it easy to create stand-alone, production-grade Spring based Applications that you can "just run". <br />


# SonarCloud Scan

<img src="https://github.com/joseosuna-engineer/junit-java/blob/main/sonar.png" align="left"  width="500" />

SonarCloud is a cloud-based code analysis service designed to detect code quality issues in 25 different programming languages, continuously ensuring the maintainability, reliability and security of your code. <br />
SonarCloud uses state-of-the-art techniques in static code analysis to find problems, and potential problems, in the code that you and your team write. <br />
Static analysis is called static because it does not rely on actually running the code (analysis of running code is called dynamic analysis). As a result, SonarCloud offers an additional layer of verification, different from automated testing and human code-review. <br />



# Mockito

<img src="https://github.com/joseosuna-engineer/junit-java/blob/main/mockito.png" align="center"  width="300" />

Mockito is an open source testing framework for Java released under the MIT License.The framework allows the creation of test double objects (mock objects) in automated unit tests for the purpose of test-driven development (TDD) or behavior-driven development (BDD).

```java
package com.prottonne;

import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MyMockitoTest {

    @InjectMocks
    private MyClass myClass;

    @Mock
    private MyMockedClass myMockedObject;

    @Test
    void testOneThing() {

        when(myMockedObject.getData()).thenReturn(Stubs.EXPECTED_DATA());

        Response response = myClass.myMethod(Stubs.REQUEST());

        // Code that tests one thing, asserts
    }

}

```

# Hamcrest

<img src="https://github.com/joseosuna-engineer/junit-java/blob/main/hamcrest.png" align="left"  width="300" />

Hamcrest is a framework that assists writing software tests in the Java programming language. It supports creating customized assertion matchers ('Hamcrest' is an anagram of 'matchers'), allowing match rules to be defined declaratively.These matchers have uses in unit testing frameworks such as JUnit and jMock.

```java
package com.prottonne;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class MyHamcrestTest {

    @InjectMocks
    private MyClass myClass;

    @Mock
    private MyMockedClass myMockedObject;

    @Test
    void testOneThing() {

        when(myMockedObject.getData()).thenReturn(Stubs.EXPECTED_DATA());

        Response response = myClass.myMethod(Stubs.REQUEST());

        assertThat(response.getSomeObject(),
                is(
                        equalTo(
                                Stubs.EXPECTED_OBJECT()
                        )
                )
        );

        assertThat(response.getSomeBoolean(),
                is(
                        Boolean.TRUE
                )
        );
    }
} 

```

# JaCoCo

<img src="https://github.com/joseosuna-engineer/junit-java/blob/main/jacoco.png" align="center"  width="500" />

JaCoCo is an open-source toolkit for measuring and reporting Java code coverage.

```xml
<plugin>
    <groupId>org.jacoco</groupId>
    <artifactId>jacoco-maven-plugin</artifactId>
    <version>0.8.6</version>
    <executions>
        <execution>
            <id>jacoco-initialize</id>
            <phase>initialize</phase>
            <goals>
                <goal>prepare-agent</goal>
            </goals>
        </execution>
        <execution>
            <id>jacoco-report</id>
            <phase>prepare-package</phase>
            <goals>
                <goal>report</goal>
            </goals>
        </execution>
    </executions>
</plugin>

```
