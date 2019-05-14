package com.loopa.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.loopa.api.irepository.IClienteRepository;
import com.loopa.api.irepository.IProfissionalRepository;
import com.loopa.api.model.Cliente;
import com.loopa.api.model.Profissional;
import com.loopa.api.security.UserSS;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private IClienteRepository clienteRepository;
	
	@Autowired 
	private IProfissionalRepository profissionalRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		/*Cliente cliente = clienteRepository.findByEmail(email);
		if (cliente == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSS(cliente.getId(), cliente.getEmail(), cliente.getSenha(), cliente.getPerfis());*/
		
		Cliente cliente = clienteRepository.findByEmail(email);
		
		if (cliente == null) {
			Profissional profissional = profissionalRepository.findByEmail(email);
			
			if(profissional == null) {
				throw new UsernameNotFoundException(email);
			}
			return new UserSS(profissional.getId(), profissional.getEmail(), profissional.getSenha(), profissional.getPerfis());
		}
		return new UserSS(cliente.getId(), cliente.getEmail(), cliente.getSenha(), cliente.getPerfis());
	}
}
