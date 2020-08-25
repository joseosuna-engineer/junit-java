# junit-java

JUnit is a unit testing framework for the Java programming language. JUnit has been important in the development of test-driven development, and is one of a family of unit testing frameworks which is collectively known as xUnit that originated with SUnit.

````
import org.junit.*;

public class FoobarTest {
    @BeforeClass
    public static void setUpClass() throws Exception {
        // Code executed before the first test method
    }

    @Before
    public void setUp() throws Exception {
        // Code executed before each test
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

    @After
    public void tearDown() throws Exception {
        // Code executed after each test 
    }
 
    @AfterClass
    public static void tearDownClass() throws Exception {
        // Code executed after the last test method 
    }
}

````

# Mockito

Mockito is an open source testing framework for Java released under the MIT License.The framework allows the creation of test double objects (mock objects) in automated unit tests for the purpose of test-driven development (TDD) or behavior-driven development (BDD).

````
package org.examples;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import org.examples.HelloApplication.HelloActable;
import org.examples.HelloApplication.HelloAction;
import org.examples.HelloApplication.Greeter;
import org.examples.HelloApplication.HelloGreeter;

public class HelloActionIntegrationTest {
   HelloActable helloAction;
   Greeter helloGreeter;
   Appendable helloWriterMock;
   
   @Before
   public void setUp() {
      helloGreeter = new HelloGreeter("welcome", " says ");
      helloWriterMock = mock(Appendable.class);
      helloAction = new HelloAction(helloGreeter, helloWriterMock);
   }
   
   @Test
   public void testSayHello() throws Exception {
      when(helloWriterMock.append(any(String.class))).thenReturn(helloWriterMock);

      helloAction.sayHello("integrationTest", "universe");

      verify(helloWriterMock, times(2)).append(any(String.class));
      verify(helloWriterMock, times(1)).append(eq("integrationTest says "));
      verify(helloWriterMock, times(1)).append(eq("welcome universe"));
   }
}

````

# Hamcrest

Hamcrest is a framework that assists writing software tests in the Java programming language. It supports creating customized assertion matchers ('Hamcrest' is an anagram of 'matchers'), allowing match rules to be defined declaratively.These matchers have uses in unit testing frameworks such as JUnit and jMock.

````
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

public class BiscuitTest {
  @Test 
  public void testEquals() { 
    Biscuit theBiscuit = new Biscuit("Ginger"); 
    Biscuit myBiscuit = new Biscuit("Ginger"); 
    assertThat(theBiscuit, equalTo(myBiscuit)); 
    assertThat("chocolate chips", theBiscuit.getChocolateChipCount(), equalTo(10)); 
    assertThat("hazelnuts", theBiscuit.getHazelnutCount(), equalTo(3));
  } 
} 

````
