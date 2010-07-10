package junit3_tdtc.tutorial_1_server;

import com.google.common.testing.junit3.TearDownTestCase;
import com.google.guiceberry.controllable.InjectionController;
import com.google.guiceberry.junit3.TearDownGuiceBerry;
import com.google.inject.Inject;

import junit3_tdtc.tutorial_1_server.prod.PetOfTheMonth;

public class Example4CanonicalSameJvmControllableInjectionTest extends TearDownTestCase {

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    TearDownGuiceBerry.setup(this, PetStoreEnv4CanonicalSameJvmControllablePotm.class);
  }
  
  @Inject
  WelcomeTestPage welcomeTestPage;
  
  @Inject
  private InjectionController<PetOfTheMonth> petOfTheMonthIc;

  public void testDogAsPotm() {
    PetOfTheMonth expected = PetOfTheMonth.DOG;
    petOfTheMonthIc.setOverride(expected);
    welcomeTestPage.goTo();
    welcomeTestPage.assertPetOfTheMonth(expected);
  }

  public void testCatAsPotm() {
    PetOfTheMonth expected = PetOfTheMonth.CAT;
    petOfTheMonthIc.setOverride(expected);
    welcomeTestPage.goTo();
    welcomeTestPage.assertPetOfTheMonth(expected);
  }
}