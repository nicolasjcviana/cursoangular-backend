package com.algaworks.api.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("algamoney")
public class AlgamoneyApiProperty {

	private final Seguranca seguranca = new Seguranca();
	
	private String origemPermitida = "http://localhost:8000";
	
	public Seguranca getSeguranca() {
		return seguranca;
	}
	
	public String getOrigemPermitida() {
		return origemPermitida;
	}
	
	public static class Seguranca {
	
		private boolean habilitarHttps;

		public boolean isHabilitarHttps() {
			return habilitarHttps;
		}

		public void setHabilitarHttps(boolean habilitarHttps) {
			this.habilitarHttps = habilitarHttps;
		}
		
	}
}
