package io.pne.veth.server.handlers.model;

import com.google.gson.annotations.SerializedName;

public class InterfaceName {

    @SerializedName("SrcName") public String              srcName;
    @SerializedName("DstPrefix") public String              dstPrefix;

}
