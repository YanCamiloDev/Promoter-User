package net.yan.kotlin.promoterapp.promoorigin.data

import android.net.Uri
import android.util.Log
import androidx.annotation.NonNull
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import net.yan.kotlin.promoterapp.promoorigin.model.Promoter

object UsuarioFirebase {
    val identificadorUsuario: String
        get() {
            val usuario = FirebaseHelper().auth
            val email = usuario?.currentUser?.email
            val identificadorUsuario = email?.let { Base64Custom.codificarBase64(it) }
            return identificadorUsuario.toString()
        }
    val usuarioAtual: FirebaseUser
        get() {
            val usuario = FirebaseHelper().auth
            return usuario?.currentUser!!
        }
    val dadosUsuarioLogado: Promoter
        get() {
            val firebaseUser = usuarioAtual
            val usuario = Promoter()
            usuario.email = firebaseUser.email!!
            usuario.nome = firebaseUser.displayName!!
            if (firebaseUser.photoUrl != null) {
                usuario.foto = firebaseUser.photoUrl.toString()
            }
            return usuario
        }

    fun atualizarNomeUsuario(nome: String): Boolean {
        try {
            val user = usuarioAtual
            val profile = UserProfileChangeRequest.Builder()
                .setDisplayName(nome)
                .build()
            user.updateProfile(profile).addOnCompleteListener {
                fun onComplete(@NonNull task: Task<Void>) {
                    if (!task.isSuccessful) {
                        Log.d("Perfil", "Erro ao atualizar nome de perfil.")
                    }
                }
            }
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

    fun atualizarFotoUsuario(url: Uri): Boolean {
        try {
            val user = usuarioAtual
            val profile = UserProfileChangeRequest.Builder()
                .setPhotoUri(url)
                .build()
            user.updateProfile(profile).addOnCompleteListener {
                fun onComplete(@NonNull task: Task<Void>) {
                    if (!task.isSuccessful) {
                        Log.d("Perfil", "Erro ao atualizar nome de perfil.")
                    }
                }
            }
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }
}