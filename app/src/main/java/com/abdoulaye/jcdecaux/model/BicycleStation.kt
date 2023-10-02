package com.abdoulaye.jcdecaux.model

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi

data class BicycleStation(
    val number: Int?,
    val contractName: String?,
    val name: String?,
    val address: String?,
    val banking: Boolean?,
    val bonus: Boolean?,
    val bike_stands: Int?,
    val available_bike_stands: Int?,
    val available_bikes: Int?,
    val status: String?,
    val last_update: Long?,
    val position: Position?,
    val totalStands: TotalStands?
) : Parcelable {
    @RequiresApi(Build.VERSION_CODES.Q)
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readBoolean(),
        parcel.readBoolean(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readLong(),
        parcel.readParcelable(Position::class.java.classLoader),
        parcel.readParcelable(TotalStands::class.java.classLoader)
    )

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        number?.let { parcel.writeInt(it) }
        parcel.writeString(contractName)
        parcel.writeString(name)
        parcel.writeString(address)
        banking?.let { parcel.writeBoolean(it) }
        bonus?.let { parcel.writeBoolean(it) }
        bike_stands?.let { parcel.writeInt(it) }
        available_bike_stands?.let { parcel.writeInt(it) }
        available_bikes?.let { parcel.writeInt(it) }
        parcel.writeString(status)
        last_update?.let { parcel.writeLong(it) }
        parcel.writeParcelable(position, flags)
        parcel.writeParcelable(totalStands, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BicycleStation> {
        @RequiresApi(Build.VERSION_CODES.Q)
        override fun createFromParcel(parcel: Parcel): BicycleStation {
            return BicycleStation(parcel)
        }

        override fun newArray(size: Int): Array<BicycleStation?> {
            return arrayOfNulls(size)
        }

    }


    data class Availabilities(
        val bikes: Int?,
        val stands: Int?,
        val mechanicalBikes: Int?,
        val electricalBikes: Int?,
        val electricalInternalBatteryBikes: Int?,
        val electricalRemovableBatteryBikes: Int?
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeValue(bikes)
            parcel.writeValue(stands)
            parcel.writeValue(mechanicalBikes)
            parcel.writeValue(electricalBikes)
            parcel.writeValue(electricalInternalBatteryBikes)
            parcel.writeValue(electricalRemovableBatteryBikes)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Availabilities> {
            override fun createFromParcel(parcel: Parcel): Availabilities {
                return Availabilities(parcel)
            }

            override fun newArray(size: Int): Array<Availabilities?> {
                return arrayOfNulls(size)
            }
        }
    }

    data class TotalStands(val availabilities: Availabilities?, val capacity: Int?) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readParcelable(Availabilities::class.java.classLoader),
            parcel.readValue(Int::class.java.classLoader) as? Int
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeParcelable(availabilities, flags)
            parcel.writeValue(capacity)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<TotalStands> {
            override fun createFromParcel(parcel: Parcel): TotalStands {
                return TotalStands(parcel)
            }

            override fun newArray(size: Int): Array<TotalStands?> {
                return arrayOfNulls(size)
            }
        }
    }

    data class Position(val lat: Long?, val ing: Long?) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readValue(Long::class.java.classLoader) as? Long,
            parcel.readValue(Long::class.java.classLoader) as? Long
        )

        override fun describeContents(): Int {
            TODO("Not yet implemented")
        }

        override fun writeToParcel(dest: Parcel?, flags: Int) {
            TODO("Not yet implemented")
        }

        companion object CREATOR : Parcelable.Creator<Position> {
            override fun createFromParcel(parcel: Parcel): Position {
                return Position(parcel)
            }

            override fun newArray(size: Int): Array<Position?> {
                return arrayOfNulls(size)
            }
        }
    }
}