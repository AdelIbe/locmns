package com.example.produits;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import com.example.produits.model.Utilisateur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private WebApplicationContext context;
	private MockMvc mvc;
	@BeforeEach
	public void setup() {
		mvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(springSecurity())
				.build();
	}

	@Test
	void creationUtilisateur_idUtilisateurNull() {
		Utilisateur utilisateur = new Utilisateur();
		assertNull(utilisateur.getId());
	}

	@Test
	void appelUrlRacine_OKattendu() throws Exception {
		mvc.perform(get("/")).andExpect(status().isOk());
	}

	@Test
	void appelUrlRacine_MessageHello() throws Exception {
		mvc.perform(get("/")).andExpect(
				content().string("Le serveur marche mais y'a rien ici")
		);
	}

	@Test
	void utilisteurNonConnecteAppelUrlListeUtilisateur_403attendu() throws Exception {
		mvc.perform(get("/utilisateurs")).andExpect(status().isForbidden());
	}

	@Test
	@WithMockUser(roles = {"UTILISATEUR"})
	void utilisteurConnecteAppelUrlListeUtilisateur_OkAttendu() throws Exception {
		mvc.perform(get("/utilisateurs")).andExpect(status().isOk());
	}
	@Test
	@WithMockUser(roles = {"ADMINISTRATEUR"})
	void administrateurAppelUrlListeUtilisateur_OkAttendu() throws Exception {
		mvc.perform(get("/utilisateurs")).andExpect(status().isOk());
	}


}
