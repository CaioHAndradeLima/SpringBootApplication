package com.mechanical.provider

import com.mechanical.cassandraRepository.User
import com.mechanical.cassandraRepository.impl.LawOfficeImpl
import com.mechanical.cassandraRepository.model.LawOffice
import com.mechanical.cassandraRepository.model.UserCassandraModel
import com.mechanical.security.AuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder

object UserProvider {

    fun provideUserAuthenticate(): UserCassandraModel? {
        return provideUser()?.user
    }


    fun provideUser(): User? {

        if (SecurityContextHolder.getContext().authentication != null) {
            return (SecurityContextHolder.getContext().authentication as AuthenticationToken).authenticatedUser.user
        }

        return null
    }

    fun provideLoggedLawOffice(user: UserCassandraModel, lawOfficeImpl: LawOfficeImpl): LawOffice {
        return provideLawOffices(user, lawOfficeImpl)!!.first()
    }

    fun provideLawOffices(user: UserCassandraModel, lawOfficeImpl: LawOfficeImpl): List<LawOffice>? {
        if (user.isLawyer) {
            return lawOfficeImpl.getAllLawOfficeByUser(user.whereWork)
        }

        return null
    }

    fun provideIdIdentifierToProcessEvents(lawOfficeImpl: LawOfficeImpl): MutableList<String> {
        return if (UserProvider.provideUser()!!.user.isLawyer) {
            /**
             * FIXME AQUI DEVERIA NA VERADE VER TODOS QUE TRABALHAM NESTE LAW OFFICE
             * E MARCAR PROCESSO PARA TODOS
             */
            mutableListOf(
                    UserProvider.provideLoggedLawOffice(
                            UserProvider.provideUserAuthenticate()!!,
                            lawOfficeImpl = lawOfficeImpl
                    ).uuid.toString()
            )
        } else {
            mutableListOf(UserProvider.provideUser()!!.user.cpf)
        }

    }

}
