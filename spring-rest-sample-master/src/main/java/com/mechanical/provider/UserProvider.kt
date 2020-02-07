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
            return lawOfficeImpl.getAllLawOfficeByUser(user)
        }

        return null
    }

    fun provideIdIdentifierToProcessEvents(lawOfficeImpl: LawOfficeImpl, user: UserCassandraModel): MutableList<String> {
        return if (user.isLawyer) {
            /**
             * FIXME AQUI DEVERIA NA VERDADE VER TODOS QUE TRABALHAM NESTE LAW OFFICE
             * E MARCAR PROCESSO PARA TODOS
             */
            mutableListOf(
                    UserProvider.provideLoggedLawOffice(
                            user,
                            lawOfficeImpl = lawOfficeImpl
                    ).uuid.toString(),
                    user.cpf
            )
        } else {
            mutableListOf(user.cpf)
        }

    }

}
