/*
 * Copyright 2022-2024 Pavel Castornii.
 *
 * This project is dual-licensed under the GNU AGPL version 3 and a commercial license.
 * See the file LICENSE.md in the root directory of the project for full license information.
 */

package com.techsenger.ansi4j.core.api.iso6429;

/**
 *
 * @author Pavel Castornii
 */
public interface ControlSequenceFunctionAlias {

    /**
     * See {@link ControlSequenceFunction#CBT}.
     */
    ControlSequenceFunction CURSOR_BACKWARD_TABULATION = ControlSequenceFunction.CBT;

    /**
     * See {@link ControlSequenceFunction#CHA}.
     */
    ControlSequenceFunction CURSOR_CHARACTER_ABSOLUTE = ControlSequenceFunction.CHA;

    /**
     * See {@link ControlSequenceFunction#CHT}.
     */
    ControlSequenceFunction CURSOR_FORWARD_TABULATION = ControlSequenceFunction.CHT;

    /**
     * See {@link ControlSequenceFunction#CNL}.
     */
    ControlSequenceFunction CURSOR_NEXT_LINE = ControlSequenceFunction.CNL;

    /**
     * See {@link ControlSequenceFunction#CPL}.
     */
    ControlSequenceFunction CURSOR_PRECEDING_LINE = ControlSequenceFunction.CPL;

    /**
     * See {@link ControlSequenceFunction#CPR}.
     */
    ControlSequenceFunction ACTIVE_POSITION_REPORT = ControlSequenceFunction.CPR;

    /**
     * See {@link ControlSequenceFunction#CTC}.
     */
    ControlSequenceFunction CURSOR_TABULATION_CONTROL = ControlSequenceFunction.CTC;

    /**
     * See {@link ControlSequenceFunction#CUB}.
     */
    ControlSequenceFunction CURSOR_LEFT = ControlSequenceFunction.CUB;

    /**
     * See {@link ControlSequenceFunction#CUD}.
     */
    ControlSequenceFunction CURSOR_DOWN = ControlSequenceFunction.CUD;

    /**
     * See {@link ControlSequenceFunction#CUF}.
     */
    ControlSequenceFunction CURSOR_RIGHT = ControlSequenceFunction.CUF;

    /**
     * See {@link ControlSequenceFunction#CUP}.
     */
    ControlSequenceFunction CURSOR_POSITION = ControlSequenceFunction.CUP;

    /**
     * See {@link ControlSequenceFunction#CUU}.
     */
    ControlSequenceFunction CURSOR_UP = ControlSequenceFunction.CUU;

    /**
     * See {@link ControlSequenceFunction#CVT}.
     */
    ControlSequenceFunction CURSOR_LINE_TABULATION = ControlSequenceFunction.CVT;

    /**
     * See {@link ControlSequenceFunction#DA}.
     */
    ControlSequenceFunction DEVICE_ATTRIBUTES = ControlSequenceFunction.DA;

    /**
     * See {@link ControlSequenceFunction#DAQ}.
     */
    ControlSequenceFunction DEFINE_AREA_QUALIFICATION = ControlSequenceFunction.DAQ;

    /**
     * See {@link ControlSequenceFunction#DCH}.
     */
    ControlSequenceFunction DELETE_CHARACTER = ControlSequenceFunction.DCH;

    /**
     * See {@link ControlSequenceFunction#DL}.
     */
    ControlSequenceFunction DELETE_LINE = ControlSequenceFunction.DL;

    /**
     * See {@link ControlSequenceFunction#DSR}.
     */
    ControlSequenceFunction DEVICE_STATUS_REPORT = ControlSequenceFunction.DSR;

    /**
     * See {@link ControlSequenceFunction#DTA}.
     */
    ControlSequenceFunction DIMENSION_TEXT_AREA = ControlSequenceFunction.DTA;

    /**
     * See {@link ControlSequenceFunction#EA}.
     */
    ControlSequenceFunction ERASE_IN_AREA = ControlSequenceFunction.EA;

    /**
     * See {@link ControlSequenceFunction#ECH}.
     */
    ControlSequenceFunction ERASE_CHARACTER = ControlSequenceFunction.ECH;

    /**
     * See {@link ControlSequenceFunction#ED}.
     */
    ControlSequenceFunction ERASE_IN_PAGE = ControlSequenceFunction.ED;

    /**
     * See {@link ControlSequenceFunction#EF}.
     */
    ControlSequenceFunction ERASE_IN_FIELD = ControlSequenceFunction.EF;

    /**
     * See {@link ControlSequenceFunction#EL}.
     */
    ControlSequenceFunction ERASE_IN_LINE = ControlSequenceFunction.EL;

    /**
     * See {@link ControlSequenceFunction#FNK}.
     */
    ControlSequenceFunction FUNCTION_KEY = ControlSequenceFunction.FNK;

    /**
     * See {@link ControlSequenceFunction#FNT}.
     */
    ControlSequenceFunction FONT_SELECTION = ControlSequenceFunction.FNT;

    /**
     * See {@link ControlSequenceFunction#GCC}.
     */
    ControlSequenceFunction GRAPHIC_CHARACTER_COMBINATION = ControlSequenceFunction.GCC;

    /**
     * See {@link ControlSequenceFunction#GSM}.
     */
    ControlSequenceFunction GRAPHIC_SIZE_MODIFICATION = ControlSequenceFunction.GSM;

    /**
     * See {@link ControlSequenceFunction#GSS}.
     */
    ControlSequenceFunction GRAPHIC_SIZE_SELECTION = ControlSequenceFunction.GSS;

    /**
     * See {@link ControlSequenceFunction#HPA}.
     */
    ControlSequenceFunction CHARACTER_POSITION_ABSOLUTE = ControlSequenceFunction.HPA;

    /**
     * See {@link ControlSequenceFunction#HPB}.
     */
    ControlSequenceFunction CHARACTER_POSITION_BACKWARD = ControlSequenceFunction.HPB;

    /**
     * See {@link ControlSequenceFunction#HPR}.
     */
    ControlSequenceFunction CHARACTER_POSITION_FORWARD = ControlSequenceFunction.HPR;

    /**
     * See {@link ControlSequenceFunction#HVP}.
     */
    ControlSequenceFunction CHARACTER_AND_LINE_POSITION = ControlSequenceFunction.HVP;

    /**
     * See {@link ControlSequenceFunction#ICH}.
     */
    ControlSequenceFunction INSERT_CHARACTER = ControlSequenceFunction.ICH;

    /**
     * See {@link ControlSequenceFunction#IDCS}.
     */
    ControlSequenceFunction IDENTIFY_DEVICE_CONTROL_STRING = ControlSequenceFunction.IDCS;

    /**
     * See {@link ControlSequenceFunction#IGS}.
     */
    ControlSequenceFunction IDENTIFY_GRAPHIC_SUBREPERTOIRE = ControlSequenceFunction.IGS;

    /**
     * See {@link ControlSequenceFunction#IL}.
     */
    ControlSequenceFunction INSERT_LINE = ControlSequenceFunction.IL;

    /**
     * See {@link ControlSequenceFunction#JFY}.
     */
    ControlSequenceFunction JUSTIFY = ControlSequenceFunction.JFY;

    /**
     * See {@link ControlSequenceFunction#MC}.
     */
    ControlSequenceFunction MEDIA_COPY = ControlSequenceFunction.MC;

    /**
     * See {@link ControlSequenceFunction#NP}.
     */
    ControlSequenceFunction NEXT_PAGE = ControlSequenceFunction.NP;

    /**
     * See {@link ControlSequenceFunction#PEC}.
     */
    ControlSequenceFunction PRESENTATION_EXPAND_OR_CONTRACT = ControlSequenceFunction.PEC;

    /**
     * See {@link ControlSequenceFunction#PFS}.
     */
    ControlSequenceFunction PAGE_FORMAT_SELECTION = ControlSequenceFunction.PFS;

    /**
     * See {@link ControlSequenceFunction#PP}.
     */
    ControlSequenceFunction PRECEDING_PAGE = ControlSequenceFunction.PP;

    /**
     * See {@link ControlSequenceFunction#PPA}.
     */
    ControlSequenceFunction PAGE_POSITION_ABSOLUTE = ControlSequenceFunction.PPA;

    /**
     * See {@link ControlSequenceFunction#PPB}.
     */
    ControlSequenceFunction PAGE_POSITION_BACKWARD = ControlSequenceFunction.PPB;

    /**
     * See {@link ControlSequenceFunction#PPR}.
     */
    ControlSequenceFunction PAGE_POSITION_FORWARD = ControlSequenceFunction.PPR;

    /**
     * See {@link ControlSequenceFunction#PTX}.
     */
    ControlSequenceFunction PARALLEL_TEXTS = ControlSequenceFunction.PTX;

    /**
     * See {@link ControlSequenceFunction#QUAD}.
     */
    ControlSequenceFunction QUAD = ControlSequenceFunction.QUAD;

    /**
     * See {@link ControlSequenceFunction#REP}.
     */
    ControlSequenceFunction REPEAT = ControlSequenceFunction.REP;

    /**
     * See {@link ControlSequenceFunction#RM}.
     */
    ControlSequenceFunction RESET_MODE = ControlSequenceFunction.RM;

    /**
     * See {@link ControlSequenceFunction#SACS}.
     */
    ControlSequenceFunction SET_ADDITIONAL_CHARACTER_SEPARATION = ControlSequenceFunction.SACS;

    /**
     * See {@link ControlSequenceFunction#SAPV}.
     */
    ControlSequenceFunction SELECT_ALTERNATIVE_PRESENTATION_VARIANTS = ControlSequenceFunction.SAPV;

    /**
     * See {@link ControlSequenceFunction#SCO}.
     */
    ControlSequenceFunction SELECT_CHARACTER_ORIENTATION = ControlSequenceFunction.SCO;

    /**
     * See {@link ControlSequenceFunction#SCP}.
     */
    ControlSequenceFunction SELECT_CHARACTER_PATH = ControlSequenceFunction.SCP;

    /**
     * See {@link ControlSequenceFunction#SCS}.
     */
    ControlSequenceFunction SET_CHARACTER_SPACING = ControlSequenceFunction.SCS;

    /**
     * See {@link ControlSequenceFunction#SD}.
     */
    ControlSequenceFunction SCROLL_DOWN = ControlSequenceFunction.SD;

    /**
     * See {@link ControlSequenceFunction#SDS}.
     */
    ControlSequenceFunction START_DIRECTED_STRING = ControlSequenceFunction.SDS;

    /**
     * See {@link ControlSequenceFunction#SEE}.
     */
    ControlSequenceFunction SELECT_EDITING_EXTENT = ControlSequenceFunction.SEE;

    /**
     * See {@link ControlSequenceFunction#SEF}.
     */
    ControlSequenceFunction SHEET_EJECT_AND_FEED = ControlSequenceFunction.SEF;

    /**
     * See {@link ControlSequenceFunction#SGR}.
     */
    ControlSequenceFunction SELECT_GRAPHIC_RENDITION = ControlSequenceFunction.SGR;

    /**
     * See {@link ControlSequenceFunction#SHS}.
     */
    ControlSequenceFunction SELECT_CHARACTER_SPACING = ControlSequenceFunction.SHS;

    /**
     * See {@link ControlSequenceFunction#SIMD}.
     */
    ControlSequenceFunction SELECT_IMPLICIT_MOVEMENT_DIRECTION = ControlSequenceFunction.SIMD;

    /**
     * See {@link ControlSequenceFunction#SL}.
     */
    ControlSequenceFunction SCROLL_LEFT = ControlSequenceFunction.SL;

    /**
     * See {@link ControlSequenceFunction#SLH}.
     */
    ControlSequenceFunction SET_LINE_HOME = ControlSequenceFunction.SLH;

    /**
     * See {@link ControlSequenceFunction#SLL}.
     */
    ControlSequenceFunction SET_LINE_LIMIT = ControlSequenceFunction.SLL;

    /**
     * See {@link ControlSequenceFunction#SLS}.
     */
    ControlSequenceFunction SET_LINE_SPACING = ControlSequenceFunction.SLS;

    /**
     * See {@link ControlSequenceFunction#SM}.
     */
    ControlSequenceFunction SET_MODE = ControlSequenceFunction.SM;

    /**
     * See {@link ControlSequenceFunction#SPD}.
     */
    ControlSequenceFunction SELECT_PRESENTATION_DIRECTIONS = ControlSequenceFunction.SPD;

    /**
     * See {@link ControlSequenceFunction#SPH}.
     */
    ControlSequenceFunction SET_PAGE_HOME = ControlSequenceFunction.SPH;

    /**
     * See {@link ControlSequenceFunction#SPI}.
     */
    ControlSequenceFunction SPACING_INCREMENT = ControlSequenceFunction.SPI;

    /**
     * See {@link ControlSequenceFunction#SPL}.
     */
    ControlSequenceFunction SET_PAGE_LIMIT = ControlSequenceFunction.SPL;

    /**
     * See {@link ControlSequenceFunction#SPQR}.
     */
    ControlSequenceFunction SELECT_PRINT_QUALITY_AND_RAPIDITY = ControlSequenceFunction.SPQR;

    /**
     * See {@link ControlSequenceFunction#SR}.
     */
    ControlSequenceFunction SCROLL_RIGHT = ControlSequenceFunction.SR;

    /**
     * See {@link ControlSequenceFunction#SRCS}.
     */
    ControlSequenceFunction SET_REDUCED_CHARACTER_SEPARATION = ControlSequenceFunction.SRCS;

    /**
     * See {@link ControlSequenceFunction#SRS}.
     */
    ControlSequenceFunction START_REVERSED_STRING = ControlSequenceFunction.SRS;

    /**
     * See {@link ControlSequenceFunction#SSU}.
     */
    ControlSequenceFunction SELECT_SIZE_UNIT = ControlSequenceFunction.SSU;

    /**
     * See {@link ControlSequenceFunction#SSW}.
     */
    ControlSequenceFunction SET_SPACE_WIDTH = ControlSequenceFunction.SSW;

    /**
     * See {@link ControlSequenceFunction#STAB}.
     */
    ControlSequenceFunction SELECTIVE_TABULATION = ControlSequenceFunction.STAB;

    /**
     * See {@link ControlSequenceFunction#SU}.
     */
    ControlSequenceFunction SCROLL_UP = ControlSequenceFunction.SU;

    /**
     * See {@link ControlSequenceFunction#SVS}.
     */
    ControlSequenceFunction SELECT_LINE_SPACING = ControlSequenceFunction.SVS;

    /**
     * See {@link ControlSequenceFunction#TAC}.
     */
    ControlSequenceFunction TABULATION_ALIGNED_CENTRED = ControlSequenceFunction.TAC;

    /**
     * See {@link ControlSequenceFunction#TALE}.
     */
    ControlSequenceFunction TABULATION_ALIGNED_LEADING_EDGE = ControlSequenceFunction.TALE;

    /**
     * See {@link ControlSequenceFunction#TATE}.
     */
    ControlSequenceFunction TABULATION_ALIGNED_TRAILING_EDGE = ControlSequenceFunction.TATE;

    /**
     * See {@link ControlSequenceFunction#TBC}.
     */
    ControlSequenceFunction TABULATION_CLEAR = ControlSequenceFunction.TBC;

    /**
     * See {@link ControlSequenceFunction#TCC}.
     */
    ControlSequenceFunction TABULATION_CENTRED_ON_CHARACTER = ControlSequenceFunction.TCC;

    /**
     * See {@link ControlSequenceFunction#TSR}.
     */
    ControlSequenceFunction TABULATION_STOP_REMOVE = ControlSequenceFunction.TSR;

    /**
     * See {@link ControlSequenceFunction#TSS}.
     */
    ControlSequenceFunction THIN_SPACE_SPECIFICATION = ControlSequenceFunction.TSS;

    /**
     * See {@link ControlSequenceFunction#VPA}.
     */
    ControlSequenceFunction LINE_POSITION_ABSOLUTE = ControlSequenceFunction.VPA;

    /**
     * See {@link ControlSequenceFunction#VPB}.
     */
    ControlSequenceFunction LINE_POSITION_BACKWARD = ControlSequenceFunction.VPB;

    /**
     * See {@link ControlSequenceFunction#VPR}.
     */
    ControlSequenceFunction LINE_POSITION_FORWARD = ControlSequenceFunction.VPR;

}
