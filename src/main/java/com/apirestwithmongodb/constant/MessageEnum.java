package com.apirestwithmongodb.constant;

public enum MessageEnum {

    // GENERAL MESSAGES

    PARAMETER_EMPTY_OR_NULL("Existem parâmetros inconsistentes..."),
    INTERNAL_SERVER_ERROR("Erro interno do servidor..."),

    // USER

    USER_NOT_FOUND("Usuário não existe ou se encontra sem acesso"),
    CREATE_USER_ERROR("Erro ao criar usuário..."),
    UPDATE_USER_ERROR("Erro ao atualizar usuário..."),
    DELETE_USER_ERROR("Erro ao excluir usuário..."),
    EMAIL_EXIST_ERROR("E-mail já vinculado a um usuário ativo do sistema..."),
    LIST_USER_SUCCESS("Listagem de usuários retornada com sucesso..."),
    CREATE_USER_SUCCESS("Usuário criado com sucesso..."),
    UPDATE_USER_SUCCESS("Usuário atualizado com sucesso..."),
    DELETE_USER_SUCCESS("Usuário excluído com sucesso...");

    private String message;

    private MessageEnum(String message) { this.message = message; }

    public String getMessage() { return message; }

}
