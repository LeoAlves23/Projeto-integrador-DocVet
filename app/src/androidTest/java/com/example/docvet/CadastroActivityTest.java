package com.example.docvet;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class CadastroActivityTest {

    @Rule
    public ActivityScenarioRule<CadastroActivity> activityRule = new ActivityScenarioRule<>(CadastroActivity.class);

    @Test
    public void testCadastroActivity() {
        // Teste para verificar se os elementos estão presentes
        Espresso.onView(ViewMatchers.withId(R.id.edtNomeDonoPet)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.edtEmailDonoPet)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.edtCpfDonoPet)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.edtTelefoneDonoPet)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.edtSenhaDonoPet)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.edtConfSenhaDonoPet)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.btnCadastrarDonoDePet)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Preencha os campos com dados de teste
        Espresso.onView(ViewMatchers.withId(R.id.edtNomeDonoPet)).perform(ViewActions.typeText("Guilherme"));
        Espresso.onView(ViewMatchers.withId(R.id.edtEmailDonoPet)).perform(ViewActions.typeText("teste@teste.com"));
        Espresso.onView(ViewMatchers.withId(R.id.edtCpfDonoPet)).perform(ViewActions.typeText("12345678901"));
        Espresso.onView(ViewMatchers.withId(R.id.edtTelefoneDonoPet)).perform(ViewActions.typeText("123456789"));
        Espresso.onView(ViewMatchers.withId(R.id.edtSenhaDonoPet)).perform(ViewActions.typeText("senha123"));
        Espresso.onView(ViewMatchers.withId(R.id.edtConfSenhaDonoPet)).perform(ViewActions.typeText("senha123"));

        // Clique no botão de cadastrar
        Espresso.onView(ViewMatchers.withId(R.id.btnCadastrarDonoDePet)).perform(ViewActions.click());
    }

    @Test
    public void testCadastroActivitySenhaValida() {
        // Teste para senhas válidas
        Espresso.onView(ViewMatchers.withId(R.id.edtNomeDonoPet)).perform(ViewActions.typeText("Guilherme"));
        Espresso.onView(ViewMatchers.withId(R.id.edtEmailDonoPet)).perform(ViewActions.typeText("teste@teste.com"));
        Espresso.onView(ViewMatchers.withId(R.id.edtCpfDonoPet)).perform(ViewActions.typeText("12345678901"));
        Espresso.onView(ViewMatchers.withId(R.id.edtTelefoneDonoPet)).perform(ViewActions.typeText("123456789"));
        Espresso.onView(ViewMatchers.withId(R.id.edtSenhaDonoPet)).perform(ViewActions.typeText("senha123"));
        Espresso.onView(ViewMatchers.withId(R.id.edtConfSenhaDonoPet)).perform(ViewActions.typeText("senha123"));

        // Clique no botão de cadastrar
        Espresso.onView(ViewMatchers.withId(R.id.btnCadastrarDonoDePet)).perform(ViewActions.click());
    }

    @Test
    public void testCadastroActivitySenhaInvalida() {
        // Teste para senhas diferentes
        Espresso.onView(ViewMatchers.withId(R.id.edtNomeDonoPet)).perform(ViewActions.typeText("Guilherme"));
        Espresso.onView(ViewMatchers.withId(R.id.edtEmailDonoPet)).perform(ViewActions.typeText("teste@teste.com"));
        Espresso.onView(ViewMatchers.withId(R.id.edtCpfDonoPet)).perform(ViewActions.typeText("12345678901"));
        Espresso.onView(ViewMatchers.withId(R.id.edtTelefoneDonoPet)).perform(ViewActions.typeText("123456789"));
        Espresso.onView(ViewMatchers.withId(R.id.edtSenhaDonoPet)).perform(ViewActions.typeText("senha123"));
        Espresso.onView(ViewMatchers.withId(R.id.edtConfSenhaDonoPet)).perform(ViewActions.typeText("outraSenha"));

        // Clique no botão de cadastrar
        Espresso.onView(ViewMatchers.withId(R.id.btnCadastrarDonoDePet)).perform(ViewActions.click());
    }

    @Test
    public void testEmailValido() {
        // Teste para e-mail válido
        Espresso.onView(ViewMatchers.withId(R.id.edtEmailDonoPet)).perform(ViewActions.typeText("teste@teste.com"));
        Espresso.onView(ViewMatchers.withId(R.id.btnCadastrarDonoDePet)).perform(ViewActions.click());

        // Clique no botão de cadastrar
        Espresso.onView(ViewMatchers.withId(R.id.btnCadastrarDonoDePet)).perform(ViewActions.click());
    }

    @Test
    public void testEmailInvalido() {
        // Teste para e-mail inválido
        Espresso.onView(ViewMatchers.withId(R.id.edtEmailDonoPet)).perform(ViewActions.typeText("email_invalido"));
        Espresso.onView(ViewMatchers.withId(R.id.btnCadastrarDonoDePet)).perform(ViewActions.click());

        // Clique no botão de cadastrar
        Espresso.onView(ViewMatchers.withId(R.id.btnCadastrarDonoDePet)).perform(ViewActions.click());
    }
}
