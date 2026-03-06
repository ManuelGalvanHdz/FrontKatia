package com.ismael.kiduaventumundo.kiduaventumundo.datasource.session
/**
 * SessionManager
 *
 * Esta clase se encarga de administrar la sesión del usuario dentro de la aplicación.
 * Su función principal es almacenar y proporcionar información relacionada con la
 * autenticación del usuario mientras la aplicación está en ejecución.
 *
 * Responsabilidades principales:
 *
 * - Guardar el token de autenticación del usuario después de iniciar sesión.
 * - Permitir verificar si existe una sesión activa.
 * - Proporcionar acceso al token para realizar peticiones autenticadas a la API.
 * - Limpiar la sesión cuando el usuario cierra sesión (logout).
 *
 * En esta implementación la sesión se mantiene en memoria, por lo que se perderá
 * cuando la aplicación se cierre. En una implementación más avanzada se puede
 * utilizar DataStore o SharedPreferences para persistir la sesión entre reinicios
 * de la aplicación.
 */


object SessionManager {

    // Token de autenticación del usuario actual
    private var token: String? = null

    // Guarda el token después de un login exitoso
    fun saveToken(newToken: String) {
        token = newToken
    }

    // Devuelve el token actual de la sesión
    fun getToken(): String? {
        return token
    }

    // Elimina la sesión cuando el usuario hace logout
    fun clearSession() {
        token = null
    }

    // Verifica si existe una sesión activa
    fun hasSession(): Boolean {
        return token != null
    }
}