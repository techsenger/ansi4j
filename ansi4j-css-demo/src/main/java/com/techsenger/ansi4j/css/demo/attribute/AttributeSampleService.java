/*
 * Copyright 2022-2024 Pavel Castornii.
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

package com.techsenger.ansi4j.css.demo.attribute;

import com.techsenger.ansi4j.css.api.GroupStyleGenerator;
import com.techsenger.ansi4j.css.api.color.XtermPalette256;
import com.techsenger.ansi4j.css.api.text.RtfxTextAreaStyleGenerator;
import com.techsenger.ansi4j.css.api.text.TextAttributeGroup;
import com.techsenger.ansi4j.css.api.text.TextFlowStyleGenerator;
import com.techsenger.ansi4j.css.api.text.WebViewStyleGenerator;
import com.techsenger.ansi4j.css.demo.Constants;
import com.techsenger.ansi4j.css.demo.content.ContentBuilder;
import com.techsenger.ansi4j.css.demo.content.RtfxTextAreaContentBuilder;
import com.techsenger.ansi4j.css.demo.content.TextFlowContentBuilder;
import com.techsenger.ansi4j.css.demo.content.WebViewContentBuilder;
import com.techsenger.ansi4j.css.demo.utils.Ansi4jUtils;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.text.Text;
import javafx.util.Pair;
import org.fxmisc.richtext.model.StyleSpans;
import static com.techsenger.ansi4j.css.api.text.TextAttributeGroup.Blinking;
import static com.techsenger.ansi4j.css.api.text.TextAttributeGroup.Underline;
import static com.techsenger.ansi4j.css.api.text.TextAttributeGroup.Weight;

/**
 *
 * @author Pavel Castornii
 */
class AttributeSampleService {

    private final TextAttributeGroup attributeGroup;

    AttributeSampleService() {
        this.attributeGroup = Ansi4jUtils.createProcessor(Constants.FG_COLOR, Constants.BG_COLOR,
            new XtermPalette256()).getAttributeRegistry().getGroup(TextAttributeGroup.KEY);
    }

    public List<AbstractAttributeSample<?>> createSamples() {
        var list = new ArrayList<AbstractAttributeSample<?>>();
        list.add(new BgColorSample(Constants.BG_COLOR, 0x4258C9, true, false, true));
        list.add(new BgColorSample(0x4258C9, Constants.BG_COLOR, true, false, true));
        list.add(new BlinkingSample(Blinking.OFF, Blinking.SLOW, true, false, false));
        list.add(new BlinkingSample(Blinking.SLOW, Blinking.OFF, false, false, false));
        list.add(new BlinkingSample(Blinking.OFF, Blinking.RAPID, true, false, false));
        list.add(new BlinkingSample(Blinking.RAPID, Blinking.OFF, false, false, false));
        list.add(new BlinkingSample(Blinking.SLOW, Blinking.RAPID, true, false, false));
        list.add(new BlinkingSample(Blinking.RAPID, Blinking.SLOW, true, false, false));
        list.add(new FgColorSample(Constants.FG_COLOR, 0xFBA71B, true, true, true));
        list.add(new FgColorSample(0xFBA71B, Constants.FG_COLOR, true, true, true));
        list.add(new FontSample(Constants.FONT_FAMILY, Constants.ALTERNATIVE_FONT_FAMILY, true, true, true));
        list.add(new FontSample(Constants.ALTERNATIVE_FONT_FAMILY, Constants.FONT_FAMILY, true, true, true));
        list.add(new ItalicSample(false, true, true, true, true));
        list.add(new ItalicSample(true, false, true, true, true));
        list.add(new ReverseVideoSample(false, true, true, false, true));
        list.add(new ReverseVideoSample(true, false, true, false, true));
        list.add(new StrikethroughSample(false, true, true, true, true));
        list.add(new StrikethroughSample(true, false, true, true, true));
        list.add(new UnderlineSample(Underline.OFF, Underline.SINGLE, true, true, true));
        list.add(new UnderlineSample(Underline.SINGLE, Underline.OFF, false, true, true));
        list.add(new UnderlineSample(Underline.OFF, Underline.DOUBLE, true, false, true));
        list.add(new UnderlineSample(Underline.DOUBLE, Underline.OFF, false, false, true));
        list.add(new UnderlineSample(Underline.SINGLE, Underline.DOUBLE, true, false, true));
        list.add(new UnderlineSample(Underline.DOUBLE, Underline.SINGLE, false, false, true));
        list.add(new VisibilitySample(true, false, true, true, true));
        list.add(new VisibilitySample(false, true, true, true, true));
        list.add(new WeightSample(Weight.NORMAL, Weight.BOLD, true, true, true));
        list.add(new WeightSample(Weight.BOLD, Weight.NORMAL, true, true, true));
        list.add(new WeightSample(Weight.FAINT, Weight.BOLD, false, false, false));
        list.add(new WeightSample(Weight.BOLD, Weight.FAINT, false, false, false));
        list.add(new WeightSample(Weight.FAINT, Weight.NORMAL, false, false, false));
        list.add(new WeightSample(Weight.NORMAL, Weight.FAINT, false, false, false));
        return list;
    }

    public SampleContents createContent(AbstractAttributeSample sample) {
        var c = new SampleContents(
                createWebViewContent(sample),
                createTextFlowContent(sample),
                createRtfxTextAreaContent(sample));
        return c;
    }

    private SampleContent<String> createWebViewContent(AbstractAttributeSample sample) {
        var contentBuilder = new WebViewContentBuilder();
        this.attributeGroup.reset();
        sample.setAttribute(sample.resolveAttribute(this.attributeGroup));
        var generator = new WebViewStyleGenerator();
        generator.initialize(this.attributeGroup);
        var styles = createContent(sample, generator, contentBuilder);
        var html = contentBuilder.build();
        html = "<span style=\"" + styles.getKey() + "\">" + html + "</span>";
        return new SampleContent<String>(html, styles.getKey(), styles.getValue());
    }

    private SampleContent<List<Text>> createTextFlowContent(AbstractAttributeSample sample) {
        var contentBuilder = new TextFlowContentBuilder();
        contentBuilder.setStyleClass("text");
        this.attributeGroup.reset();
        sample.setAttribute(sample.resolveAttribute(this.attributeGroup));
        var generator = new TextFlowStyleGenerator();
        generator.initialize(this.attributeGroup);
        var styles = createContent(sample, generator, contentBuilder);
        var texts = contentBuilder.build();
        return new SampleContent<List<Text>>(texts, styles.getKey(), styles.getValue());
    }

    private SampleContent<Pair<String, StyleSpans<String>>> createRtfxTextAreaContent(AbstractAttributeSample sample) {
        var contentBuilder = new RtfxTextAreaContentBuilder();
        this.attributeGroup.reset();
        sample.setAttribute(sample.resolveAttribute(this.attributeGroup));
        var generator = new RtfxTextAreaStyleGenerator();
        generator.initialize(this.attributeGroup);
        var styles = createContent(sample, generator, contentBuilder);
        var pair = contentBuilder.build();
        return new SampleContent<Pair<String, StyleSpans<String>>>(pair, styles.getKey(), styles.getValue());
    }

    private Pair<String, String> createContent(AbstractAttributeSample sample, GroupStyleGenerator<?> generator,
            ContentBuilder<?> contentBuilder) {
        sample.prepareForDefaultStyle();
        var defaultDeclarations = generator.generate();
        var defaultStyle = "";
        if (!defaultDeclarations.isEmpty()) {
            defaultStyle = String.join(";", defaultDeclarations);
        }
        sample.prepareForCurrentStyle();
        contentBuilder.appendText("Default style string");
        contentBuilder.terminateLine();
        var sampleDeclarations = generator.generate();
        var currentStyle = "";
        if (!sampleDeclarations.isEmpty()) {
            currentStyle = String.join(";", sampleDeclarations);
            contentBuilder.startStyle(currentStyle);
        }
        contentBuilder.appendText("Current style string");
        if (!sampleDeclarations.isEmpty()) {
            contentBuilder.endStyle();
        }
        contentBuilder.terminateLine();
        return new Pair<>(defaultStyle, currentStyle);
    }
}
