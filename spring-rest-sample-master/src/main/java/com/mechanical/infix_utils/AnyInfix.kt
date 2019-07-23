package com.mechanical.infix_utils

import com.mechanical.endpoint.gson


fun Any.toJson(): String = gson.toJson(this)