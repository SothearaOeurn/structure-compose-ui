package com.kh.sbilyhour.composestructure.data.datasource.local.datastore

import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object UserPreferencesSerializer : Serializer<User.UserProto> {
    override val defaultValue: User.UserProto = User.UserProto.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): User.UserProto {
        return try {
            User.UserProto.parseFrom(input)
        } catch (e: InvalidProtocolBufferException) {
            defaultValue
        }
    }

    override suspend fun writeTo(t: User.UserProto, output: OutputStream) {
        t.writeTo(output)
    }
}