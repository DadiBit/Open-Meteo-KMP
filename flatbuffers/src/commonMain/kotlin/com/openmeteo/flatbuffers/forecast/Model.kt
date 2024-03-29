// automatically generated by the FlatBuffers compiler, do not modify

package com.openmeteo.flatbuffers.forecast

import com.google.flatbuffers.kotlin.*
import kotlin.jvm.JvmInline
@Suppress("unused")
@JvmInline
value class Model (val value: UByte) {
    companion object {
        val Undefined = Model(0u)
        val BestMatch = Model(1u)
        val GfsSeamless = Model(2u)
        val GfsGlobal = Model(3u)
        val GfsHrrr = Model(4u)
        val MeteofranceSeamless = Model(5u)
        val MeteofranceArpegeSeamless = Model(6u)
        val MeteofranceArpegeWorld = Model(7u)
        val MeteofranceArpegeEurope = Model(8u)
        val MeteofranceAromeSeamless = Model(9u)
        val MeteofranceAromeFrance = Model(10u)
        val MeteofranceAromeFranceHd = Model(11u)
        val JmaSeamless = Model(12u)
        val JmaMsm = Model(13u)
        val JmsGsm = Model(14u)
        val JmaGsm = Model(15u)
        val GemSeamless = Model(16u)
        val GemGlobal = Model(17u)
        val GemRegional = Model(18u)
        val GemHrdpsContinental = Model(19u)
        val IconSeamless = Model(20u)
        val IconGlobal = Model(21u)
        val IconEu = Model(22u)
        val IconD2 = Model(23u)
        val EcmwfIfs04 = Model(24u)
        val MetnoNordic = Model(25u)
        val Era5Seamless = Model(26u)
        val Era5 = Model(27u)
        val Cerra = Model(28u)
        val Era5Land = Model(29u)
        val EcmwfIfs = Model(30u)
        val Gwam = Model(31u)
        val Ewam = Model(32u)
        val GlofasSeamlessV3 = Model(33u)
        val GlofasForecastV3 = Model(34u)
        val GlofasConsolidatedV3 = Model(35u)
        val GlofasSeamlessV4 = Model(36u)
        val GlofasForecastV4 = Model(37u)
        val GlofasConsolidatedV4 = Model(38u)
        val Gfs025 = Model(39u)
        val Gfs05 = Model(40u)
        val CmccCm2Vhr4 = Model(41u)
        val FgoalsF3HHighresSst = Model(42u)
        val FgoalsF3H = Model(43u)
        val HiRamSitHr = Model(44u)
        val MriAgcm32S = Model(45u)
        val EcEarth3PHr = Model(46u)
        val MpiEsm12Xr = Model(47u)
        val Nicam168S = Model(48u)
        val CamsEurope = Model(49u)
        val CamsGlobal = Model(50u)
        val Cfsv2 = Model(51u)
        val Era5Ocean = Model(52u)
        val CmaGrapesGlobal = Model(53u)
        val BomAccessGlobal = Model(54u)
        val BomAccessGlobalEnsemble = Model(55u)
        val names : Array<String> = arrayOf("undefined", "best_match", "gfs_seamless", "gfs_global", "gfs_hrrr", "meteofrance_seamless", "meteofrance_arpege_seamless", "meteofrance_arpege_world", "meteofrance_arpege_europe", "meteofrance_arome_seamless", "meteofrance_arome_france", "meteofrance_arome_france_hd", "jma_seamless", "jma_msm", "jms_gsm", "jma_gsm", "gem_seamless", "gem_global", "gem_regional", "gem_hrdps_continental", "icon_seamless", "icon_global", "icon_eu", "icon_d2", "ecmwf_ifs04", "metno_nordic", "era5_seamless", "era5", "cerra", "era5_land", "ecmwf_ifs", "gwam", "ewam", "glofas_seamless_v3", "glofas_forecast_v3", "glofas_consolidated_v3", "glofas_seamless_v4", "glofas_forecast_v4", "glofas_consolidated_v4", "gfs025", "gfs05", "CMCC_CM2_VHR4", "FGOALS_f3_H_highresSST", "FGOALS_f3_H", "HiRAM_SIT_HR", "MRI_AGCM3_2_S", "EC_Earth3P_HR", "MPI_ESM1_2_XR", "NICAM16_8S", "cams_europe", "cams_global", "cfsv2", "era5_ocean", "cma_grapes_global", "bom_access_global", "bom_access_global_ensemble")
        fun name(e: Model) : String = names[e.value.toInt()]
    }
}

