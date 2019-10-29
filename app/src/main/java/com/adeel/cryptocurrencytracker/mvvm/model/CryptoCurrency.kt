package com.adeel.cryptocurrencytracker.mvvm.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CryptoCurrency(
    @Json(name = "status")
    var status: Status? = null,
    @Json(name = "data")
    var `data`: List<Data?>? = null
) {
    @JsonClass(generateAdapter = true)
    data class Status(
        @Json(name = "timestamp")
        var timestamp: String? = null,
        @Json(name = "error_code")
        var errorCode: Int? = null,
        @Json(name = "error_message")
        var errorMessage: Any? = null,
        @Json(name = "elapsed")
        var elapsed: Int? = null,
        @Json(name = "credit_count")
        var creditCount: Int? = null,
        @Json(name = "notice")
        var notice: Any? = null
    )

    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "id")
        var id: Int? = null,
        @Json(name = "name")
        var name: String? = null,
        @Json(name = "symbol")
        var symbol: String? = null,
        @Json(name = "slug")
        var slug: String? = null,
        @Json(name = "num_market_pairs")
        var numMarketPairs: Int? = null,
        @Json(name = "date_added")
        var dateAdded: String? = null,
        @Json(name = "tags")
        var tags: List<String?>? = null,
        @Json(name = "max_supply")
        var maxSupply: Double? = null,
        @Json(name = "circulating_supply")
        var circulatingSupply: Double? = null,
        @Json(name = "total_supply")
        var totalSupply: Double? = null,
        @Json(name = "cmc_rank")
        var cmcRank: Int? = null,
        @Json(name = "last_updated")
        var lastUpdated: String? = null,
        @Json(name = "quote")
        var quote: Quote? = null,
        @Json(name = "platform")
        var platform: Platform? = null
    ) {
        @JsonClass(generateAdapter = true)
        data class Quote(
            @Json(name = "USD")
            var uSD: USD? = null
        ) {
            @JsonClass(generateAdapter = true)
            data class USD(
                @Json(name = "price")
                var price: Double? = null,
                @Json(name = "volume_24h")
                var volume24h: Double? = null,
                @Json(name = "percent_change_1h")
                var percentChange1h: Double? = null,
                @Json(name = "percent_change_24h")
                var percentChange24h: Double? = null,
                @Json(name = "percent_change_7d")
                var percentChange7d: Double? = null,
                @Json(name = "market_cap")
                var marketCap: Double? = null,
                @Json(name = "last_updated")
                var lastUpdated: String? = null
            )
        }

        @JsonClass(generateAdapter = true)
        data class Platform(
            @Json(name = "id")
            var id: Int? = null,
            @Json(name = "name")
            var name: String? = null,
            @Json(name = "symbol")
            var symbol: String? = null,
            @Json(name = "slug")
            var slug: String? = null,
            @Json(name = "token_address")
            var tokenAddress: String? = null
        )
    }
}