package org.agmip.core.translators;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import org.agmip.core.types.AdvancedHashMap;
import org.agmip.util.JSONAdapter;

/**
 * DSSAT Observation Data I/O API Class
 *
 * @author Meng Zhang
 * @version 1.0
 */
public class DssatTFileOutput extends DssatCommonOutput {

    /**
     * DSSAT Observation Data Output method
     *
     * @param arg0 file name(?)
     * @param result data holder object
     */
    @Override
    public void writeFile(String arg0, AdvancedHashMap result) {

        // Initial variables
        JSONAdapter adapter = new JSONAdapter();    // JSON Adapter
        AdvancedHashMap<String, Object> record;     // Data holder for daily data
        AdvancedHashMap<String, Object> data;       // Data holder for whole Observation data
        BufferedWriter br;                   // output object
        StringBuilder sbData = new StringBuilder();     // construct the data info in the output
        HashMap titleList = new HashMap();          // Define necessary observation data fields
        // TODO Add neccessary fields here
        HashMap altTitleList = new HashMap();        // Define alternative fields for the necessary observation data fields
        // TODO Add alternative fields here
        HashMap optTitleList = new HashMap();        // Define optional observation data fields
        HashMap titleOutput = new HashMap();         // contain output data field id
        optTitleList.put("EnoAD", "E#AD");
        optTitleList.put("CHTD", "CHTD");
        optTitleList.put("NInoM", "NI#M");
        optTitleList.put("AMLC", "AMLC");
        optTitleList.put("AMLS", "AMLS");
        optTitleList.put("AWAG", "AWAG");
        optTitleList.put("BWAD", "BWAD");
        optTitleList.put("CASM", "CASM");
        optTitleList.put("CDAD", "CDAD");
        optTitleList.put("CDAY", "CDAY");
        optTitleList.put("CDVD", "CDVD");
        optTitleList.put("CEW", "CEW");
        optTitleList.put("CFAD", "CFAD");
        optTitleList.put("CGRD", "CGRD");
        optTitleList.put("CHAD", "CHAD");
        optTitleList.put("CHNpct", "CHN%");
        optTitleList.put("CHNAD", "CHNAD");
        optTitleList.put("CHPpct", "CHP%");
        optTitleList.put("CHPAD", "CHPAD");
        optTitleList.put("CHWAD", "CHWAD");
        optTitleList.put("CLpctD", "CL%D");
        optTitleList.put("CLAI", "CLAI");
        optTitleList.put("CLFM", "CLFM");
        optTitleList.put("CMAD", "CMAD");
        optTitleList.put("CNpctD", "CN%D");
        optTitleList.put("CNAD", "CNAD");
        optTitleList.put("CO20C", "CO20C");
        optTitleList.put("CO2FP", "CO2FP");
        optTitleList.put("CO2SC", "CO2SC");
        optTitleList.put("CPpctD", "CP%D");
        optTitleList.put("CPAD", "CPAD");
        optTitleList.put("CPOpct", "CPO%");
        optTitleList.put("CRAD", "CRAD");
        optTitleList.put("CRLF", "CRLF");
        optTitleList.put("CRLV", "CRLV");
        optTitleList.put("CRTM", "CRTM");
        optTitleList.put("CSpctD", "CS%D");
        optTitleList.put("CSDno", "CSD#");
        optTitleList.put("CSDM", "CSDM");
        optTitleList.put("CSHno", "CSH#");
        optTitleList.put("CSHM", "CSHM");
        optTitleList.put("CSTM", "CSTM");
        optTitleList.put("CWAD", "CWAD");
        optTitleList.put("CWID", "CWID");
        optTitleList.put("CWPD", "CWPD");
        optTitleList.put("DAP", "DAP");
        optTitleList.put("DAS", "DAS");
        optTitleList.put("DASM", "DASM");
        optTitleList.put("fd_DAY", "DAY");
        optTitleList.put("DAYLF", "DAYLF");
        optTitleList.put("DLA", "DLA");
        optTitleList.put("DLApct", "DLA%");
        optTitleList.put("DLAI", "DLAI");
        optTitleList.put("DLFM", "DLFM");
        optTitleList.put("DNAG", "DNAG");
        optTitleList.put("DPAG", "DPAG");
        optTitleList.put("DPOpct", "DPO%");
        optTitleList.put("DRLF", "DRLF");
        optTitleList.put("DRLV", "DRLV");
        optTitleList.put("DRNC", "DRNC");
        optTitleList.put("DRTM", "DRTM");
        optTitleList.put("DSDno", "DSD#");
        optTitleList.put("DSDM", "DSDM");
        optTitleList.put("DSHno", "DSH#");
        optTitleList.put("DSHM", "DSHM");
        optTitleList.put("DSTM", "DSTM");
        optTitleList.put("DWAD", "DWAD");
        optTitleList.put("DWAG", "DWAG");
        optTitleList.put("EAID", "EAID");
        optTitleList.put("EFAC", "EFAC");
        optTitleList.put("EFAD", "EFAD");
        optTitleList.put("EGWA", "EGWA");
        optTitleList.put("EGWS", "EGWS");
        optTitleList.put("EOAD", "EOAD");
        optTitleList.put("EPAA", "EPAA");
        optTitleList.put("EPAD", "EPAD");
        optTitleList.put("ESAA", "ESAA");
        optTitleList.put("ESAC", "ESAC");
        optTitleList.put("ESAD", "ESAD");
        optTitleList.put("ETAA", "ETAA");
        optTitleList.put("ETAC", "ETAC");
        optTitleList.put("ETAD", "ETAD");
        optTitleList.put("EWAD", "EWAD");
        optTitleList.put("EWSD", "EWSD");
        optTitleList.put("FALG", "FALG");
        optTitleList.put("FALI", "FALI");
        optTitleList.put("FAW", "FAW");
        optTitleList.put("FDEN", "FDEN");
        optTitleList.put("FL3C", "FL3C");
        optTitleList.put("FL3N", "FL3N");
        optTitleList.put("FL4C", "FL4C");
        optTitleList.put("FL4N", "FL4N");
        optTitleList.put("FLAGW", "FLAGW");
        optTitleList.put("FLBD", "FLBD");
        optTitleList.put("FLDD", "FLDD");
        optTitleList.put("FLNI", "FLNI");
        optTitleList.put("FLPH", "FLPH");
        optTitleList.put("FLTI", "FLTI");
        optTitleList.put("FLUR", "FLUR");
        optTitleList.put("FROD", "FROD");
        optTitleList.put("FUHY", "FUHY");
        optTitleList.put("FWAD", "FWAD");
        optTitleList.put("GnoAD", "G#AD");
        optTitleList.put("GnoPD", "G#PD");
        optTitleList.put("GnoSD", "G#SD");
        optTitleList.put("GCpctD", "GC%D");
        optTitleList.put("GLpctD", "GL%D");
        optTitleList.put("GNpctD", "GN%D");
        optTitleList.put("GNAD", "GNAD");
        optTitleList.put("GPpctD", "GP%D");
        optTitleList.put("GPAD", "GPAD");
        optTitleList.put("GRAD", "GRAD");
        optTitleList.put("GSTD", "GSTD");
        optTitleList.put("GWAD", "GWAD");
        optTitleList.put("GWGD", "GWGD");
        optTitleList.put("H2OA", "H2OA");
        optTitleList.put("HCRD", "HCRD");
        optTitleList.put("HIAD", "HIAD");
        optTitleList.put("HIND", "HIND");
        optTitleList.put("HIPD", "HIPD");
        optTitleList.put("HUM1", "HUM1");
        optTitleList.put("HUM2", "HUM2");
        optTitleList.put("HUM3", "HUM3");
        optTitleList.put("HUM4", "HUM4");
        optTitleList.put("HUM5", "HUM5");
        optTitleList.put("HWAD", "HWAD");
        optTitleList.put("HYAD", "HYAD");
        optTitleList.put("INFD", "INFD");
        optTitleList.put("IRnoC", "IR#C");
        optTitleList.put("IRRC", "IRRC");
        optTitleList.put("IRRD", "IRRD");
        optTitleList.put("LnoIR", "L#IR");
        optTitleList.put("LnoSD", "L#SD");
        optTitleList.put("LApctD", "LA%D");
        optTitleList.put("LAFD", "LAFD");
        optTitleList.put("LAID", "LAID");
        optTitleList.put("LALD", "LALD");
        optTitleList.put("LALN", "LALN");
        optTitleList.put("LAPD", "LAPD");
        optTitleList.put("LARD", "LARD");
        optTitleList.put("LC0D", "LC0D");
        optTitleList.put("LC1D", "LC1D");
        optTitleList.put("LC2D", "LC2D");
        optTitleList.put("LC3D", "LC3D");
        optTitleList.put("LC4D", "LC4D");
        optTitleList.put("LC5D", "LC5D");
        optTitleList.put("LCNF", "LCNF");
        optTitleList.put("LCTD", "LCTD");
        optTitleList.put("LDAD", "LDAD");
        optTitleList.put("LIpctD", "LI%D");
        optTitleList.put("LIpctN", "LI%N");
        optTitleList.put("LIWAD", "LIWAD");
        optTitleList.put("LMHN", "LMHN");
        optTitleList.put("LMLN", "LMLN");
        optTitleList.put("LNpctD", "LN%D");
        optTitleList.put("LN0D", "LN0D");
        optTitleList.put("LN1D", "LN1D");
        optTitleList.put("LN2D", "LN2D");
        optTitleList.put("LN3D", "LN3D");
        optTitleList.put("LN4D", "LN4D");
        optTitleList.put("LN5D", "LN5D");
        optTitleList.put("LNAD", "LNAD");
        optTitleList.put("LNTD", "LNTD");
        optTitleList.put("LNUM", "LNUM");
        optTitleList.put("LPpctD", "LP%D");
        optTitleList.put("LP0D", "LP0D");
        optTitleList.put("LP1D", "LP1D");
        optTitleList.put("LP2D", "LP2D");
        optTitleList.put("LP3D", "LP3D");
        optTitleList.put("LP4D", "LP4D");
        optTitleList.put("LP5D", "LP5D");
        optTitleList.put("LPAD", "LPAD");
        optTitleList.put("LPTD", "LPTD");
        optTitleList.put("LRSD", "LRSD");
        optTitleList.put("LWAD", "LWAD");
        optTitleList.put("LWPD", "LWPD");
        optTitleList.put("MEC0D", "MEC0D");
        optTitleList.put("MEC1D", "MEC1D");
        optTitleList.put("MEC2D", "MEC2D");
        optTitleList.put("MEC3D", "MEC3D");
        optTitleList.put("MEC4D", "MEC4D");
        optTitleList.put("MEC5D", "MEC5D");
        optTitleList.put("MECTD", "MECTD");
        optTitleList.put("MEN0D", "MEN0D");
        optTitleList.put("MEN1D", "MEN1D");
        optTitleList.put("MEN2D", "MEN2D");
        optTitleList.put("MEN3D", "MEN3D");
        optTitleList.put("MEN4D", "MEN4D");
        optTitleList.put("MEN5D", "MEN5D");
        optTitleList.put("MENTD", "MENTD");
        optTitleList.put("MEP0D", "MEP0D");
        optTitleList.put("MEP1D", "MEP1D");
        optTitleList.put("MEP2D", "MEP2D");
        optTitleList.put("MEP3D", "MEP3D");
        optTitleList.put("MEP4D", "MEP4D");
        optTitleList.put("MEP5D", "MEP5D");
        optTitleList.put("MEPTD", "MEPTD");
        optTitleList.put("MRAD", "MRAD");
        optTitleList.put("NpctHN", "N%HN");
        optTitleList.put("NpctLN", "N%LN");
        optTitleList.put("NAPC", "NAPC");
        optTitleList.put("NDNC", "NDNC");
        optTitleList.put("NFGD", "NFGD");
        optTitleList.put("NFPD", "NFPD");
        optTitleList.put("NFSD", "NFSD");
        optTitleList.put("NFTID", "NFTID");
        optTitleList.put("NFUD", "NFUD");
        optTitleList.put("NFXC", "NFXC");
        optTitleList.put("NFXD", "NFXD");
        optTitleList.put("NFXM", "NFXM");
        optTitleList.put("NH10D", "NH10D");
        optTitleList.put("NH1D", "NH1D");
        optTitleList.put("NH2D", "NH2D");
        optTitleList.put("NH3D", "NH3D");
        optTitleList.put("NH4D", "NH4D");
        optTitleList.put("NH5D", "NH5D");
        optTitleList.put("NH6D", "NH6D");
        optTitleList.put("NH7D", "NH7D");
        optTitleList.put("NH8D", "NH8D");
        optTitleList.put("NH9D", "NH9D");
        optTitleList.put("NHTD", "NHTD");
        optTitleList.put("NHU1", "NHU1");
        optTitleList.put("NHU2", "NHU2");
        optTitleList.put("NHU3", "NHU3");
        optTitleList.put("NHU4", "NHU4");
        optTitleList.put("NHU5", "NHU5");
        optTitleList.put("NIAD", "NIAD");
        optTitleList.put("NICM", "NICM");
        optTitleList.put("NIMC", "NIMC");
        optTitleList.put("NITC", "NITC");
        optTitleList.put("NITD", "NITD");
        optTitleList.put("NLCC", "NLCC");
        optTitleList.put("NMNC", "NMNC");
        optTitleList.put("NO10D", "NO10D");
        optTitleList.put("NO1D", "NO1D");
        optTitleList.put("NO2D", "NO2D");
        optTitleList.put("NO3D", "NO3D");
        optTitleList.put("NO4D", "NO4D");
        optTitleList.put("NO5D", "NO5D");
        optTitleList.put("NO6D", "NO6D");
        optTitleList.put("NO7D", "NO7D");
        optTitleList.put("NO8D", "NO8D");
        optTitleList.put("NO9D", "NO9D");
        optTitleList.put("NOAD", "NOAD");
        optTitleList.put("NSPAV", "NSPAV");
        optTitleList.put("NSTD", "NSTD");
        optTitleList.put("NTOP", "NTOP");
        optTitleList.put("NUAC", "NUAC");
        optTitleList.put("NUAD", "NUAD");
        optTitleList.put("NUPC", "NUPC");
        optTitleList.put("NUPR", "NUPR");
        optTitleList.put("NWAD", "NWAD");
        optTitleList.put("OnoAD", "O#AD");
        optTitleList.put("OBS_TRT_ID", "OBS_TRT_ID");
        optTitleList.put("OMAC", "OMAC");
        optTitleList.put("OWAD", "OWAD");
        optTitleList.put("OWGD", "OWGD");
        optTitleList.put("OXRN", "OXRN");
        optTitleList.put("PnoAD", "P#AD");
        optTitleList.put("PnoAM", "P#AM");
        optTitleList.put("PAC1D", "PAC1D");
        optTitleList.put("PAC2D", "PAC2D");
        optTitleList.put("PAC3D", "PAC3D");
        optTitleList.put("PAC4D", "PAC4D");
        optTitleList.put("PAC5D", "PAC5D");
        optTitleList.put("PACTD", "PACTD");
        optTitleList.put("PARI", "PARI");
        optTitleList.put("PARUE", "PARUE");
        optTitleList.put("PAVTD", "PAVTD");
        optTitleList.put("PHAD", "PHAD");
        optTitleList.put("PHAN", "PHAN");
        optTitleList.put("PInoM", "PI#M");
        optTitleList.put("PICM", "PICM");
        optTitleList.put("PIMC", "PIMC");
        optTitleList.put("PITTD", "PITTD");
        optTitleList.put("PLB1D", "PLB1D");
        optTitleList.put("PLB2D", "PLB2D");
        optTitleList.put("PLB3D", "PLB3D");
        optTitleList.put("PLB4D", "PLB4D");
        optTitleList.put("PLB5D", "PLB5D");
        optTitleList.put("PLBTD", "PLBTD");
        optTitleList.put("PLCC", "PLCC");
        optTitleList.put("PLPpctD", "PLP%D");
        optTitleList.put("PLPAD", "PLPAD");
        optTitleList.put("PMpctM", "PM%M");
        optTitleList.put("PMAD", "PMAD");
        optTitleList.put("PMNC", "PMNC");
        optTitleList.put("PREC", "PREC");
        optTitleList.put("PST1A", "PST1A");
        optTitleList.put("PST1D", "PST1D");
        optTitleList.put("PST2A", "PST2A");
        optTitleList.put("PST2D", "PST2D");
        optTitleList.put("PST3D", "PST3D");
        optTitleList.put("PST4D", "PST4D");
        optTitleList.put("PST5D", "PST5D");
        optTitleList.put("PSTTD", "PSTTD");
        optTitleList.put("PTF", "PTF");
        optTitleList.put("PUPC", "PUPC");
        optTitleList.put("PUPD", "PUPD");
        optTitleList.put("PWAD", "PWAD");
        optTitleList.put("PWDD", "PWDD");
        optTitleList.put("RCNF", "RCNF");
        optTitleList.put("RDAD", "RDAD");
        optTitleList.put("RDPD", "RDPD");
        optTitleList.put("RESC", "RESC");
        optTitleList.put("RESNC", "RESNC");
        optTitleList.put("RESPC", "RESPC");
        optTitleList.put("RGRD", "RGRD");
        optTitleList.put("RL10D", "RL10D");
        optTitleList.put("RL1D", "RL1D");
        optTitleList.put("RL2D", "RL2D");
        optTitleList.put("RL3D", "RL3D");
        optTitleList.put("RL4D", "RL4D");
        optTitleList.put("RL5D", "RL5D");
        optTitleList.put("RL6D", "RL6D");
        optTitleList.put("RL7D", "RL7D");
        optTitleList.put("RL8D", "RL8D");
        optTitleList.put("RL9D", "RL9D");
        optTitleList.put("RLAD", "RLAD");
        optTitleList.put("RLWD", "RLWD");
        optTitleList.put("RNpctD", "RN%D");
        optTitleList.put("RNAD", "RNAD");
        optTitleList.put("RNUA", "RNUA");
        optTitleList.put("ROFC", "ROFC");
        optTitleList.put("ROFD", "ROFD");
        optTitleList.put("RSpctD", "RS%D");
        optTitleList.put("RSAD", "RSAD");
        optTitleList.put("RSFP", "RSFP");
        optTitleList.put("RSNAD", "RSNAD");
        optTitleList.put("RSPAD", "RSPAD");
        optTitleList.put("RSPD", "RSPD");
        optTitleList.put("RSVN", "RSVN");
        optTitleList.put("RSWAD", "RSWAD");
        optTitleList.put("RTMPD", "RTMPD");
        optTitleList.put("RTOPD", "RTOPD");
        optTitleList.put("RTPpctD", "RTP%D");
        optTitleList.put("RTPAD", "RTPAD");
        optTitleList.put("RTWM", "RTWM");
        optTitleList.put("RWAD", "RWAD");
        optTitleList.put("RWLD", "RWLD");
        optTitleList.put("SnoAD", "S#AD");
        optTitleList.put("SnoPD", "S#PD");
        optTitleList.put("S1C0D", "S1C0D");
        optTitleList.put("S1C1D", "S1C1D");
        optTitleList.put("S1C2D", "S1C2D");
        optTitleList.put("S1C3D", "S1C3D");
        optTitleList.put("S1C4D", "S1C4D");
        optTitleList.put("S1C5D", "S1C5D");
        optTitleList.put("S1CTD", "S1CTD");
        optTitleList.put("S1N0D", "S1N0D");
        optTitleList.put("S1N1D", "S1N1D");
        optTitleList.put("S1N2D", "S1N2D");
        optTitleList.put("S1N3D", "S1N3D");
        optTitleList.put("S1N4D", "S1N4D");
        optTitleList.put("S1N5D", "S1N5D");
        optTitleList.put("S1NTD", "S1NTD");
        optTitleList.put("S1P0D", "S1P0D");
        optTitleList.put("S1P1D", "S1P1D");
        optTitleList.put("S1P2D", "S1P2D");
        optTitleList.put("S1P3D", "S1P3D");
        optTitleList.put("S1P4D", "S1P4D");
        optTitleList.put("S1P5D", "S1P5D");
        optTitleList.put("S1PTD", "S1PTD");
        optTitleList.put("S2C1D", "S2C1D");
        optTitleList.put("S2C2D", "S2C2D");
        optTitleList.put("S2C3D", "S2C3D");
        optTitleList.put("S2C4D", "S2C4D");
        optTitleList.put("S2C5D", "S2C5D");
        optTitleList.put("S2CTD", "S2CTD");
        optTitleList.put("S2N1D", "S2N1D");
        optTitleList.put("S2N2D", "S2N2D");
        optTitleList.put("S2N3D", "S2N3D");
        optTitleList.put("S2N4D", "S2N4D");
        optTitleList.put("S2N5D", "S2N5D");
        optTitleList.put("S2NTD", "S2NTD");
        optTitleList.put("S3C1D", "S3C1D");
        optTitleList.put("S3C2D", "S3C2D");
        optTitleList.put("S3C3D", "S3C3D");
        optTitleList.put("S3C4D", "S3C4D");
        optTitleList.put("S3C5D", "S3C5D");
        optTitleList.put("S3CTD", "S3CTD");
        optTitleList.put("S3N1D", "S3N1D");
        optTitleList.put("S3N2D", "S3N2D");
        optTitleList.put("S3N3D", "S3N3D");
        optTitleList.put("S3N4D", "S3N4D");
        optTitleList.put("S3N5D", "S3N5D");
        optTitleList.put("S3NTD", "S3NTD");
        optTitleList.put("SAID", "SAID");
        optTitleList.put("SC1D", "SC1D");
        optTitleList.put("SC2D", "SC2D");
        optTitleList.put("SC3D", "SC3D");
        optTitleList.put("SC4D", "SC4D");
        optTitleList.put("SC5D", "SC5D");
        optTitleList.put("SCDD", "SCDD");
        optTitleList.put("SCNF", "SCNF");
        optTitleList.put("SCTD", "SCTD");
        optTitleList.put("SCWA", "SCWA");
        optTitleList.put("SDAD", "SDAD");
        optTitleList.put("SDMPD", "SDMPD");
        optTitleList.put("SDNpctD", "SDN%D");
        optTitleList.put("SDNAD", "SDNAD");
        optTitleList.put("SDOPD", "SDOPD");
        optTitleList.put("SDPpctD", "SDP%D");
        optTitleList.put("SDPAD", "SDPAD");
        optTitleList.put("SDWAD", "SDWAD");
        optTitleList.put("SDWT", "SDWT");
        optTitleList.put("SEAD", "SEAD");
        optTitleList.put("SEDM", "SEDM");
        optTitleList.put("SENC0", "SENC0");
        optTitleList.put("SENCS", "SENCS");
        optTitleList.put("SENL0", "SENL0");
        optTitleList.put("SENLS", "SENLS");
        optTitleList.put("SENN0", "SENN0");
        optTitleList.put("SENNS", "SENNS");
        optTitleList.put("SENNT", "SENNT");
        optTitleList.put("SENWT", "SENWT");
        optTitleList.put("SGSB", "SGSB");
        optTitleList.put("SHpctD", "SH%D");
        optTitleList.put("SHAD", "SHAD");
        optTitleList.put("SHMPD", "SHMPD");
        optTitleList.put("SHND", "SHND");
        optTitleList.put("SHOPD", "SHOPD");
        optTitleList.put("SHPpctD", "SHP%D");
        optTitleList.put("SHPAD", "SHPAD");
        optTitleList.put("SHWAD", "SHWAD");
        optTitleList.put("SL", "SL");
        optTitleList.put("SLAD", "SLAD");
        optTitleList.put("SLHN", "SLHN");
        optTitleList.put("SLLN", "SLLN");
        optTitleList.put("SLMPD", "SLMPD");
        optTitleList.put("SLND", "SLND");
        optTitleList.put("SLOPD", "SLOPD");
        optTitleList.put("SLPpctD", "SLP%D");
        optTitleList.put("SLPAD", "SLPAD");
        optTitleList.put("SLPF", "SLPF");
        optTitleList.put("SNpctD", "SN%D");
        optTitleList.put("SN1D", "SN1D");
        optTitleList.put("SN2D", "SN2D");
        optTitleList.put("SN3D", "SN3D");
        optTitleList.put("SN4D", "SN4D");
        optTitleList.put("SN5D", "SN5D");
        optTitleList.put("SNAD", "SNAD");
        optTitleList.put("SNDD", "SNDD");
        optTitleList.put("SNN0C", "SNN0C");
        optTitleList.put("SNN1C", "SNN1C");
        optTitleList.put("SNP0C", "SNP0C");
        optTitleList.put("SNP1C", "SNP1C");
        optTitleList.put("SNTD", "SNTD");
        optTitleList.put("SNW0C", "SNW0C");
        optTitleList.put("SNW1C", "SNW1C");
        optTitleList.put("SOCD", "SOCD");
        optTitleList.put("SPnoP", "SP#P");
        optTitleList.put("SPpctD", "SP%D");
        optTitleList.put("SPAD", "SPAD");
        optTitleList.put("SPAM", "SPAM");
        optTitleList.put("SSAD", "SSAD");
        optTitleList.put("STC0D", "STC0D");
        optTitleList.put("STC1D", "STC1D");
        optTitleList.put("STC2D", "STC2D");
        optTitleList.put("STC3D", "STC3D");
        optTitleList.put("STC4D", "STC4D");
        optTitleList.put("STC5D", "STC5D");
        optTitleList.put("STCTD", "STCTD");
        optTitleList.put("STN0D", "STN0D");
        optTitleList.put("STN1D", "STN1D");
        optTitleList.put("STN2D", "STN2D");
        optTitleList.put("STN3D", "STN3D");
        optTitleList.put("STN4D", "STN4D");
        optTitleList.put("STN5D", "STN5D");
        optTitleList.put("STNTD", "STNTD");
        optTitleList.put("SUAD", "SUAD");
        optTitleList.put("SUGD", "SUGD");
        optTitleList.put("SUID", "SUID");
        optTitleList.put("SW10D", "SW10D");
        optTitleList.put("SW1D", "SW1D");
        optTitleList.put("SW2D", "SW2D");
        optTitleList.put("SW3D", "SW3D");
        optTitleList.put("SW4D", "SW4D");
        optTitleList.put("SW5D", "SW5D");
        optTitleList.put("SW6D", "SW6D");
        optTitleList.put("SW7D", "SW7D");
        optTitleList.put("SW8D", "SW8D");
        optTitleList.put("SW9D", "SW9D");
        optTitleList.put("SWAD", "SWAD");
        optTitleList.put("SWPD", "SWPD");
        optTitleList.put("SWXD", "SWXD");
        optTitleList.put("TnoAD", "T#AD");
        optTitleList.put("TnoPD", "T#PD");
        optTitleList.put("TnoSD", "T#SD");
        optTitleList.put("TAID", "TAID");
        optTitleList.put("TDRW", "TDRW");
        optTitleList.put("TDWA", "TDWA");
        optTitleList.put("TFGD", "TFGD");
        optTitleList.put("TFON", "TFON");
        optTitleList.put("TFPD", "TFPD");
        optTitleList.put("TGAV", "TGAV");
        optTitleList.put("TGNN", "TGNN");
        optTitleList.put("TKILL", "TKILL");
        optTitleList.put("TNAD", "TNAD");
        optTitleList.put("TPAD", "TPAD");
        optTitleList.put("TRAD", "TRAD");
        optTitleList.put("TRRD", "TRRD");
        optTitleList.put("TUNA", "TUNA");
        optTitleList.put("TWAD", "TWAD");
        optTitleList.put("UNpctD", "UN%D");
        optTitleList.put("UNAD", "UNAD");
        optTitleList.put("UPpctD", "UP%D");
        optTitleList.put("UPAD", "UPAD");
        optTitleList.put("UWAD", "UWAD");
        optTitleList.put("UYAD", "UYAD");
        optTitleList.put("VBC5", "VBC5");
        optTitleList.put("VBC6", "VBC6");
        optTitleList.put("VNpctD", "VN%D");
        optTitleList.put("VNAD", "VNAD");
        optTitleList.put("VPpctD", "VP%D");
        optTitleList.put("VPAD", "VPAD");
        optTitleList.put("VRNF", "VRNF");
        optTitleList.put("WAVR", "WAVR");
        optTitleList.put("WFGD", "WFGD");
        optTitleList.put("WFPD", "WFPD");
        optTitleList.put("WFSD", "WFSD");
        optTitleList.put("WFTD", "WFTD");
        optTitleList.put("WFTID", "WFTID");
        optTitleList.put("WLVG", "WLVG");
        optTitleList.put("WSGD", "WSGD");
        optTitleList.put("WSPAV", "WSPAV");
        optTitleList.put("WSPD", "WSPD");
        optTitleList.put("WUPR", "WUPR");
        optTitleList.put("fd_YEAR", "YEAR");
        Set titleListId = titleList.keySet();
        Set optTitleListId = optTitleList.keySet();
        int trno = 1;
//        File file;
//        FileWriter output;

        try {

            // Set default value for missing data
            setDefVal();

            // Get Data from input holder
            ArrayList observeRecords;
            try {
                observeRecords = (ArrayList) result.getOr("observed", new ArrayList());
            } catch (ClassCastException e) {
                observeRecords = new ArrayList();
                observeRecords.add(result.getOr("observed", new LinkedHashMap()));
            }

            // Initial BufferedWriter
            String exName = getExName(result);
            String fileName = exName;
            if (fileName.equals("")) {
                fileName = "a.tmp";
            } else {
                fileName = fileName.substring(0, fileName.length() - 2) + "." + fileName.substring(fileName.length() - 2) + "T";
            }
            br = new BufferedWriter(new FileWriter(new File(fileName)));

            // Output Observation File
            // Titel Section
            sbData.append(String.format("*EXP. DATA (T): %1$-10s %2$s\r\n\r\n", exName, result.getOr("local_name", defValC).toString()));

            // Check if all necessary field is available
            //Map obvTemp = new LinkedHashMap();
            //obvTemp.put("observed", observeRecords);
            //AdvancedHashMap obvData = adapter.exportRecord(obvTemp);
            AdvancedHashMap obvData = adapter.exportRecord((Map) result.getOr("observed", new AdvancedHashMap())); // TODO 
            for (Object title : titleListId) {
                // check which optional data is exist, if not, remove from map
                if (!obvData.getOr(title.toString(), "").toString().equals("")) {
                    titleOutput.put(title, titleList.get(title));
                } else if (altTitleList.containsKey(title) && !obvData.getOr(altTitleList.get(title).toString(), "").toString().equals("")) {
                    titleOutput.put(altTitleList.get(title), titleList.get(title));
                } else {
                    // TODO throw new Exception("Incompleted record because missing data : [" + title + "]");
                    sbError.append("! Waring: Incompleted record because missing data : [").append(title).append("]\r\n");
                }
            }

            // Check if which optional field is available
            for (Object title : optTitleListId) {
                // check which optional data is exist, if not, remove from map
                if (!obvData.getOr(title.toString(), "").toString().equals("")) {
                    if (!titleOutput.containsKey(title)) {
                        titleOutput.put(title, optTitleList.get(title));
                    }

                }
            }

            // Observation Data Section
            Object[] titleOutputId = titleOutput.keySet().toArray();
            for (int i = 0; i < (titleOutputId.length / 39 + titleOutputId.length % 39 == 0 ? 0 : 1); i++) {

                sbData.append("@TRNO   DATE");
                int limit = Math.min(titleOutputId.length, (i + 1) * 39);
                for (int j = i * 39; j < limit; j++) {
                    sbData.append(titleOutput.get(titleOutputId[j]).toString());
                }
                sbData.append("\r\n");

                for (int j = 0; j < observeRecords.size(); j++) {

                    record = adapter.exportRecord((Map) observeRecords.get(j));
                    sbData.append(String.format(" %1$5d", trno));
                    sbData.append(String.format(" %1$5d", formatDateStr(record.getOr("date", defValI).toString()))); // TODO waiting for the id
                    for (int k = i * 40; k < limit; k++) {
                        
                        sbData.append(" ").append(formatNumStr(5, record.getOr(titleOutputId[k].toString(), defValI).toString()));
                    
                    }
                    sbData.append("\r\n");
                }

            }

            // Output finish
            br.write(sbError.toString());
            br.write(sbData.toString());
            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Set default value for missing data
     *
     * @version 1.0
     */
    private void setDefVal() {

        // defValD = ""; No need to set default value for Date type in Observation file
        defValR = "-99";
        defValC = "";
        defValI = "-99";
    }
}
