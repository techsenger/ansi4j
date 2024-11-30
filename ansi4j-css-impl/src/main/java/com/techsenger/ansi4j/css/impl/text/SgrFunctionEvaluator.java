/*
 * Copyright 2024 Pavel Castornii.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.techsenger.ansi4j.css.impl.text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import com.techsenger.ansi4j.core.api.FunctionFragment;
import com.techsenger.ansi4j.core.api.function.Function;
import com.techsenger.ansi4j.core.api.function.FunctionArgument;
import com.techsenger.ansi4j.core.api.iso6429.ControlSequenceFunction;
import com.techsenger.ansi4j.core.api.iso6429.SgrParameterValue;
import com.techsenger.ansi4j.css.api.attribute.Attribute;
import com.techsenger.ansi4j.css.api.attribute.AttributeChange;
import com.techsenger.ansi4j.css.api.attribute.AttributeRegistry;
import com.techsenger.ansi4j.css.api.color.SgrExtraColorValue;
import static com.techsenger.ansi4j.css.impl.text.TextAttributeGroupImpl.Blinking;
import static com.techsenger.ansi4j.css.impl.text.TextAttributeGroupImpl.Underline;
import static com.techsenger.ansi4j.css.impl.text.TextAttributeGroupImpl.Weight;
import com.techsenger.ansi4j.css.impl.FunctionEvaluator;
import com.techsenger.ansi4j.css.api.ProcessorResult;
import com.techsenger.ansi4j.css.api.text.FontIndex;
import com.techsenger.ansi4j.css.api.text.TextAttributeGroup;
import com.techsenger.ansi4j.css.impl.ProcessorResultImpl;
import java.util.Objects;
import java.util.TreeSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Currently supports only SGR functions.
 *
 * @author Pavel Castornii
 */
public class SgrFunctionEvaluator implements FunctionEvaluator {

    private static final Logger logger = LoggerFactory.getLogger(SgrFunctionEvaluator.class);

    private abstract static class AbstractEvaluator<T> {

        private final Attribute<T> attribute;

        private List<Integer> parameters;

        AbstractEvaluator(Attribute<T> attribute, List<Integer> parameters) {
            this.attribute = attribute;
            this.parameters = parameters;
        }

        abstract void evaluate(FunctionArgument argument, Queue<FunctionArgument> argumentQueue,
                ProcessorResultImpl result);

        public Attribute<T> getAttribute() {
            return attribute;
        }

        public List<Integer> getParameters() {
            return parameters;
        }

        protected void doChange(T newValue,  ProcessorResultImpl result) {
            if (!Objects.equals(attribute.getValue(), newValue)) {
                var change = new AttributeChange(attribute, attribute.getValue(), newValue);
                result.getAttributeChanges().add(change);
            }
        }
    }

    private final class WeightEvaluator extends AbstractEvaluator<Weight> {

        WeightEvaluator() {
            super(attributeGroup.getWeightAttribute(), List.of(
                    SgrParameterValue.BOLD_OR_INCREASED_INTENSITY,
                    SgrParameterValue.FAINT_OR_DECREASED_INTENSITY_OR_SECOND_COLOUR,
                    SgrParameterValue.NORMAL_COLOUR_OR_NORMAL_INTENSITY
            ));
        }

        @Override
        void evaluate(FunctionArgument argument, Queue<FunctionArgument> argumentQueue, ProcessorResultImpl result) {
            Weight value;
            switch ((Integer) argument.getValue()) {
                case SgrParameterValue.BOLD_OR_INCREASED_INTENSITY:
                    value = Weight.BOLD;
                    break;
                case SgrParameterValue.FAINT_OR_DECREASED_INTENSITY_OR_SECOND_COLOUR:
                    value = Weight.FAINT;
                    break;
                case SgrParameterValue.NORMAL_COLOUR_OR_NORMAL_INTENSITY:
                    value = Weight.NORMAL;
                    break;
                default:
                    throw new AssertionError();
            }
            doChange(value, result);
        }
    }

    private final class UnderlineEvaluator extends AbstractEvaluator<Underline> {

        UnderlineEvaluator() {
            super(attributeGroup.getUnderlineAttribute(), List.of(
                    SgrParameterValue.SINGLY_UNDERLINED,
                    SgrParameterValue.DOUBLY_UNDERLINED,
                    SgrParameterValue.NOT_UNDERLINED
            ));
        }

        @Override
        void evaluate(FunctionArgument argument, Queue<FunctionArgument> argumentQueue, ProcessorResultImpl result) {
            Underline value;
            switch ((Integer) argument.getValue()) {
                case SgrParameterValue.SINGLY_UNDERLINED:
                    value = Underline.SINGLE;
                    break;
                case SgrParameterValue.DOUBLY_UNDERLINED:
                    value = Underline.DOUBLE;
                    break;
                case SgrParameterValue.NOT_UNDERLINED:
                    value = Underline.OFF;
                    break;
                default:
                    throw new AssertionError();
            }
            doChange(value, result);
        }
    }

    private final class BlinkingEvaluator extends AbstractEvaluator<Blinking> {

        BlinkingEvaluator() {
            super(attributeGroup.getBlinkingAttribute(), List.of(
                SgrParameterValue.SLOWLY_BLINKING,
                SgrParameterValue.RAPIDLY_BLINKING,
                SgrParameterValue.STEADY
            ));
        }

        @Override
        void evaluate(FunctionArgument argument, Queue<FunctionArgument> argumentQueue, ProcessorResultImpl result) {
            Blinking value;
            switch ((Integer) argument.getValue()) {
                case SgrParameterValue.SLOWLY_BLINKING:
                    value = Blinking.SLOW;
                    break;
                case SgrParameterValue.RAPIDLY_BLINKING:
                    value = Blinking.RAPID;
                    break;
                case SgrParameterValue.STEADY:
                    value = Blinking.OFF;
                    break;
                default:
                    throw new AssertionError();
            }
            doChange(value, result);
        }
    }

    private final class VisibilityEvaluator extends AbstractEvaluator<Boolean> {

        VisibilityEvaluator() {
            super(attributeGroup.getVisibilityAttribute(), List.of(
                SgrParameterValue.CONCEALED_CHARACTERS,
                SgrParameterValue.REVEALED_CHARACTERS
            ));
        }

        @Override
        void evaluate(FunctionArgument argument, Queue<FunctionArgument> argumentQueue, ProcessorResultImpl result) {
            Boolean value;
            switch ((Integer) argument.getValue()) {
                case SgrParameterValue.CONCEALED_CHARACTERS:
                    value = false;
                    break;
                case SgrParameterValue.REVEALED_CHARACTERS:
                    value = true;
                    break;
                default:
                    throw new AssertionError();
            }
            doChange(value, result);
        }
    }

    private static final class BooleanEvaluator extends AbstractEvaluator<Boolean> {

        BooleanEvaluator(Attribute<Boolean> attribute, Integer onParam, Integer offParam) {
            super(attribute, List.of(onParam, offParam));
        }

        @Override
        void evaluate(FunctionArgument argument, Queue<FunctionArgument> argumentQueue, ProcessorResultImpl result) {
            Boolean value;
            if ((Integer) argument.getValue() == getParameters().get(0)) {
                value = true;
            } else {
                value = false;
            }
            doChange(value, result);
        }
    }

    private abstract class AbstractColorEvaluator extends AbstractEvaluator<Integer>  {

        AbstractColorEvaluator(Attribute<Integer> attribute, List<Integer> params) {
            super(attribute, params);
        }

        @Override
        void evaluate(FunctionArgument argument, Queue<FunctionArgument> argumentQueue, ProcessorResultImpl result) {
            var argumentValue = (int) argument.getValue();
            var attribute = getAttribute();
            var config = attributeGroup.getConfig();
            //now we find new value
            Integer newColor = null;
            Integer attributeValue = resolveAttributeValue(argumentValue);
            if (!config.areExtraColorsEnabled()) {
                var colorIndex = attributeValue;
                newColor = config.getPalette8().getColors()[colorIndex];
            } else {
                if (argumentValue == SgrExtraColorValue.DISPLAY_8_OR_24_BIT_PALETTE
                        || argumentValue == SgrExtraColorValue.BACKGROUND_8_OR_24_BIT_PALETTE) {
                    //now we need second argument.
                    var nextArgument = argumentQueue.peek();
                    int nextArgumentValue = (int) nextArgument.getValue();
                    if (nextArgumentValue == SgrExtraColorValue.PALETTE_8_BIT) {
                        argumentQueue.poll(); //now remove
                        int colorIndex = (int) argumentQueue.poll().getValue();
                        newColor = config.getPalette256().getColors()[colorIndex];
                    } else if (nextArgumentValue == SgrExtraColorValue.PALETTE_24_BIT) {
                        argumentQueue.poll(); //now remove
                        int red = (int) argumentQueue.poll().getValue();
                        int green = (int) argumentQueue.poll().getValue();
                        int blue = (int) argumentQueue.poll().getValue();
                        newColor = (red << 16) | (green << 8) | blue;
                    }
                } else {
                    var colorIndex = attributeValue;
                    newColor = config.getPalette16().getColors()[colorIndex];
                }
            }
            if (attribute.getValue() != newColor) {
                var change = new AttributeChange<Integer>(attribute, attribute.getValue(), newColor);
                result.getAttributeChanges().add(change);
            }
        }

        protected abstract Integer resolveAttributeValue(Integer argumentValue);

    };

    private final class FgColorEvaluator extends AbstractColorEvaluator {

        FgColorEvaluator() {
            super(attributeGroup.getFgColorAttribute(), List.of(
                    //30-37
                    SgrParameterValue.BLACK_DISPLAY,
                    SgrParameterValue.RED_DISPLAY,
                    SgrParameterValue.GREEN_DISPLAY,
                    SgrParameterValue.YELLOW_DISPLAY,
                    SgrParameterValue.BLUE_DISPLAY,
                    SgrParameterValue.MAGENTA_DISPLAY,
                    SgrParameterValue.CYAN_DISPLAY,
                    SgrParameterValue.WHITE_DISPLAY,
                    //4 bit palette
                    //90-97
                    SgrExtraColorValue.BRIGHT_BLACK_DISPLAY,
                    SgrExtraColorValue.BRIGHT_RED_DISPLAY,
                    SgrExtraColorValue.BRIGHT_GREEN_DISPLAY,
                    SgrExtraColorValue.BRIGHT_YELLOW_DISPLAY,
                    SgrExtraColorValue.BRIGHT_BLUE_DISPLAY,
                    SgrExtraColorValue.BRIGHT_MAGENTA_DISPLAY,
                    SgrExtraColorValue.BRIGHT_CYAN_DISPLAY,
                    SgrExtraColorValue.BRIGHT_WHITE_DISPLAY,
                    //8 bit and 24 bit palette
                    SgrExtraColorValue.DISPLAY_8_OR_24_BIT_PALETTE
            ));
        }

        @Override
        protected Integer resolveAttributeValue(Integer argumentValue) {
            if (argumentValue == SgrExtraColorValue.DISPLAY_8_OR_24_BIT_PALETTE) {
                return -1;
            } else if (argumentValue <= 37) {
                return argumentValue - 30;
            } else {
                return argumentValue - 82;
            }
        }

    }

    private final class BgColorEvaluator extends AbstractColorEvaluator {

        BgColorEvaluator() {
            super(attributeGroup.getBgColorAttribute(), List.of(
                    //40-47
                    SgrParameterValue.BLACK_BACKGROUND,
                    SgrParameterValue.RED_BACKGROUND,
                    SgrParameterValue.GREEN_BACKGROUND,
                    SgrParameterValue.YELLOW_BACKGROUND,
                    SgrParameterValue.BLUE_BACKGROUND,
                    SgrParameterValue.MAGENTA_BACKGROUND,
                    SgrParameterValue.CYAN_BACKGROUND,
                    SgrParameterValue.WHITE_BACKGROUND,
                    //4 bit palette
                    //100-107
                    SgrExtraColorValue.BRIGHT_BLACK_BACKGROUND,
                    SgrExtraColorValue.BRIGHT_RED_BACKGROUND,
                    SgrExtraColorValue.BRIGHT_GREEN_BACKGROUND,
                    SgrExtraColorValue.BRIGHT_YELLOW_BACKGROUND,
                    SgrExtraColorValue.BRIGHT_BLUE_BACKGROUND,
                    SgrExtraColorValue.BRIGHT_MAGENTA_BACKGROUND,
                    SgrExtraColorValue.BRIGHT_CYAN_BACKGROUND,
                    SgrExtraColorValue.BRIGHT_WHITE_BACKGROUND,
                    //8 bit and 24 bit palette
                    SgrExtraColorValue.BACKGROUND_8_OR_24_BIT_PALETTE
            ));
        }

        @Override
        protected Integer resolveAttributeValue(Integer argumentValue) {
            if (argumentValue == SgrExtraColorValue.DISPLAY_8_OR_24_BIT_PALETTE) {
                return -1;
            } else if (argumentValue <= 47) {
                return argumentValue - 40;
            } else {
                return argumentValue - 92;
            }
        }
    }

    private final class DefaultColorEvaluator extends AbstractEvaluator<Integer>  {

        DefaultColorEvaluator(Attribute<Integer> attribute, int param) {
            super(attribute, List.of(param));
        }

        @Override
        void evaluate(FunctionArgument argument, Queue<FunctionArgument> argumentQueue, ProcessorResultImpl result) {
            doChange(getAttribute().getDefaultValue(), result);
        }
    };

    private final class FontEvaluator extends AbstractEvaluator<String> {

        FontEvaluator() {
            super(attributeGroup.getFontAttribute(), List.of(
                    SgrParameterValue.PRIMARY_FONT,
                    SgrParameterValue.FIRST_ALTERNATIVE_FONT,
                    SgrParameterValue.SECOND_ALTERNATIVE_FONT,
                    SgrParameterValue.THIRD_ALTERNATIVE_FONT,
                    SgrParameterValue.FOURTH_ALTERNATIVE_FONT,
                    SgrParameterValue.FIFTH_ALTERNATIVE_FONT,
                    SgrParameterValue.SIXTH_ALTERNATIVE_FONT,
                    SgrParameterValue.SEVENTH_ALTERNATIVE_FONT,
                    SgrParameterValue.EIGHTH_ALTERNATIVE_FONT,
                    SgrParameterValue.NINTH_ALTERNATIVE_FONT
            ));
        }

        @Override
        void evaluate(FunctionArgument argument, Queue<FunctionArgument> argumentQueue, ProcessorResultImpl result) {
            var argumentValue = (Integer) argument.getValue();
            doChange(getFont(argumentValue - 10), result);
        }

        private String getFont(int index) {
            var families = attributeGroup.getConfig().getFontFamilies();
            if (index < families.size()) {
                return families.get(index);
            } else {
                //not to get errors when font is not provided
                return families.get(FontIndex.DEFAULT);
            }
        }

    }

    private final class ResetEvaluator extends AbstractEvaluator<Object> {

        ResetEvaluator() {
            super(null, List.of(SgrParameterValue.DEFAULT_RENDITION));
        }

        @Override
        void evaluate(FunctionArgument argument, Queue<FunctionArgument> argumentQueue, ProcessorResultImpl result) {
            for (var attribute : attributeGroup.getAttributes()) {
                if (!attribute.isValueDefault()) {
                    var change = new AttributeChange(attribute, attribute.getValue(), attribute.getDefaultValue());
                    result.getAttributeChanges().add(change);
                }
            }
        }
    }

    /**
     * Descriptors by parameter values. This map hold data for converting SGR values Attribute values.
     */
    private final Map<Integer, AbstractEvaluator<?>> evaluatorsByParameter = new HashMap<>();

    private final TextAttributeGroupImpl attributeGroup;

    /**
     * Constructor.
     */
    public SgrFunctionEvaluator(AttributeRegistry registry) {
        this.attributeGroup = (TextAttributeGroupImpl) registry.getGroup(TextAttributeGroup.KEY);
        List<AbstractEvaluator<?>> evaluators = new ArrayList<>();
        evaluators.add(new WeightEvaluator());
        evaluators.add(new UnderlineEvaluator());
        evaluators.add(new BlinkingEvaluator());
        evaluators.add(new VisibilityEvaluator());
        evaluators.add(new BooleanEvaluator(this.attributeGroup.getItalicAttribute(),
                SgrParameterValue.ITALICIZED, SgrParameterValue.NOT_ITALICIZED_OR_NOT_FRAKTUR));
        evaluators.add(new BooleanEvaluator(this.attributeGroup.getReverseVideoAttribute(),
                SgrParameterValue.NEGATIVE_IMAGE, SgrParameterValue.POSITIVE_IMAGE));
        evaluators.add(new BooleanEvaluator(this.attributeGroup.getStrikethroughAttribute(),
                SgrParameterValue.CROSSED_OUT, SgrParameterValue.NOT_CROSSED_OUT));
        evaluators.add(new FgColorEvaluator());
        evaluators.add(new BgColorEvaluator());
        evaluators.add(new DefaultColorEvaluator(this.attributeGroup.getFgColorAttribute(),
                SgrParameterValue.DEFAULT_COLOUR_DISPLAY));
        evaluators.add(new DefaultColorEvaluator(this.attributeGroup.getBgColorAttribute(),
                SgrParameterValue.DEFAULT_BACKGROUND_COLOUR));
        evaluators.add(new FontEvaluator());
        evaluators.add(new ResetEvaluator());

        for (var eval : evaluators) {
            eval.getParameters().forEach(p -> evaluatorsByParameter.put(p, eval));
        }
        if (logger.isDebugEnabled()) {
            var params = new TreeSet<Integer>(evaluatorsByParameter.keySet());
            logger.debug("Initialized SGR evaluator for params: {}", params);
        }
    }

    @Override
    public void evaluate(FunctionFragment functionFragment, ProcessorResult result) {
        //we need fifo queue, because every attribute processing function can retrieve additional arguments.
        Queue<FunctionArgument> argumentQueue = new LinkedList<>(functionFragment.getArguments());
        var r = (ProcessorResultImpl) result;
        while (!argumentQueue.isEmpty()) {
            var argument = argumentQueue.poll();
            int argumentValue = (int) argument.getValue();
            var evaluator = this.evaluatorsByParameter.get(argumentValue);
            if (evaluator != null) {
                evaluator.evaluate(argument, argumentQueue, r);
            }
        }

    }

    @Override
    public Function getFunction() {
        return ControlSequenceFunction.SGR;
    }
}



