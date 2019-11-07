package com.mechanical.endpoint.view

import com.mechanical.cassandraRepository.model.event.EventCassandraModel

data class PagePrincipalOut(
    val isNeededShowAddProcess :Boolean = false,
    val isPossibleShowListEvents:Boolean = false,
    val listEvents: MutableList<EventCassandraModel>? = null
)