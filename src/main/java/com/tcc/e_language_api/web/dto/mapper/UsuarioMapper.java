package com.tcc.e_language_api.web.dto.mapper;

import com.tcc.e_language_api.entity.Usuario;
import com.tcc.e_language_api.web.dto.EnderecoDto;
import com.tcc.e_language_api.web.dto.UsuarioDto;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapper {
    private final static ModelMapper modelMapper = new ModelMapper();
    public static Usuario toEntity(UsuarioDto usuarioDto, Usuario usuario) {
        usuarioDto.setUsuarioId(usuario.getUsuarioId());
        modelMapper.map(usuarioDto, usuario);
        usuario.getEnderecos().forEach(endereco -> {
            EnderecoDto dto = usuarioDto.getEnderecos().get(usuario.getEnderecos().indexOf(endereco));
            modelMapper.map(dto, endereco);
            endereco.setUsuario(usuario);
        });
        return usuario;
    }

    public static UsuarioDto toDto(Usuario Usuario) {
        return modelMapper.map(Usuario, UsuarioDto.class);
    }

    public static List<UsuarioDto> toListDto(List<Usuario> usuarios) {
        return usuarios.stream().map(Usuario -> toDto(Usuario)).collect(Collectors.toList());
    }
}
