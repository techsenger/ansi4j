# Techsenger Ansi4j
* [Overview](#ansi4j-overview)
    * [Core](#ansi4j-core)
        * [Overview](#ansi4j-core-overview)
        * [Base components](#ansi4j-core-base)
        * [Dependencies](#ansi4j-core-dependencies)
        * [Usage](#ansi4j-core-usage)
        * [Thread-safety](#ansi4j-core-thread)
    * [CSS extension](#ansi4j-css)
        * [Overview](#ansi4j-css-overview)
        * [Demo](#ansi4j-css-demo)
        * [Base components](#ansi4j-css-base)
        * [Dependencies](#ansi4j-css-dependencies)
        * [Usage](#ansi4j-css-usage)
        * [Thread-safety](#ansi4j-css-thread)
    * [Code building](#ansi4j-building)
    * [Feedback](#ansi4j-feedback)
* [Theory](#theory)
    * [Control character](#theory-control)
    * [Control character and encoding](#theory-encoding)
        * [Control character in ASCII](#theory-encoding-ascii)
        * [Control character in Unicode](#theory-encoding-unicode)
    * [Control Function Standards](#theory-standard)
        * [ECMA](#theory-standard-ecma)
        * [ANSI](#theory-standard-ansi)
        * [ISO](#theory-standard-iso)
    * [Types of control functions](#theory-types)
    * [Parameter examples](#theory-parameters)
* [Useful resources](#resources)

# Overview <a name="ansi4j-overview"></a>

Techsenger Ansi4j is a Java library that parses ANSI escape codes in full accordance with ISO/IEC 6429:1992.
It supports all functions of all five types. At the same time parser architecture allows to add easily any other custom
types of functions and mechanism to process them.

Techsenger Ansi4j consists of a core that includes parser and CSS extension that allows to create CSS declaration using
function arguments.

## Core <a name="ansi4j-core"></a>

### Overview <a name="ansi4j-core-overview"></a>

Core contains all base classes for working with control functions, parsers and text.

### Base components <a name="ansi4j-core-base"></a>

* `ParserFactory` is thread-safe instance of factory, that can be used for creating N parsers for parsing N texts.
So, usually there is only one factory.
* `Parser` is a non thread-safe object that reads text, manages finder and handlers and returns parsed fragment.
There are two types of parsers:
    * `StringParser` for parsing `String`. `StringParser` is very light, so it is possible to create it for every text line.
    * `StreamParser` for parsing `InputStream`. One instance of `StreamParser` is created for one instance of `InputStream`.
* `FunctionFinder` finds function in a text and resolves found function.
* `FragmentHandler` is a thread-safe object for processing fragment of text. There are two types of handlers:
    * `TextHandler` is a handler for processing a text that doesn't contain any control functions in it.
      This handler allows to modify this text within system. Default implementation doesn't modify text and just wraps
      it in `TextFragment`.
    * `FunctionHandler` is a handler for processing functions in a text. For every type of function separate function
    handler exists. As a result `FunctionHandler` returns `FunctionFragment`.
* `Fragment` is a processed piece of text. There are two types of fragments:
    * `TextFragment` that contains information about text pieces without functions.
    * `FunctionFragment` that contains information about functions in text.
* `ControlFunction` is the base interface implemented by all enumerations used for ANSI function identification. These
enumerations contain short function names in accordance with the standard. At the same time, for each enumeration X,
there is an XAlias interface that provides the full function names. These aliases can be used by those who do not
remember the short names.

### Dependencies <a name="ansi4j-core-dependencies"></a>

This project is available on Maven Central:

    <dependency>
        <groupId>com.techsenger.ansi4j</groupId>
        <artifactId>ansi4j-core-api</artifactId>
        <version>2.0.0</version>
    </dependency>
    <dependency>
        <groupId>com.techsenger.ansi4j</groupId>
        <artifactId>ansi4j-core-impl</artifactId>
        <version>2.0.0</version>
    </dependency>

### Usage <a name="ansi4j-core-usage"></a>

Step 0 - Creating `ParserFactory`

    ParserFactory factory = new ParserFactory.Builder()
            .environment(Environment._7_BIT)
            //if you don't need some types of functions just don't provide handlers for them
            .functionTypes(
                    ControlFunctionType.C0_SET,
                    ControlFunctionType.C1_SET,
                    ControlFunctionType.CONTROL_SEQUENCE,
                    ControlFunctionType.INDEPENDENT_FUNCTION,
                    ControlFunctionType.CONTROL_STRING)
            .build();

Step 1A - Creating `StringParser`

    //this is the text we are going to parse
    String text = ...;

    //we need a parser
    var parser = factory.createParser(text);

Step 1B - Creating `StreamParser`

    //this is the stream we are going to parse
    InputStream stream = ...;

    //we need a parser, one parser for one stream
    try (var parser = factory.createParser(stream, StandardCharsets.UTF_8, 1024)) {
        ...
    } catch (IOException ex) {
        ...
    }

Step 2 - Parsing

    //so, let's go
    Fragment fragment = null;
    while ((fragment = parser.parse()) != null) {
        if (fragment.getType() == FragmentType.TEXT) {
            TextFragment textFragment = (TextFragment) fragment;
            ...
        } else if (fragment.getType() == FragmentType.FUNCTION) {
            FunctionFragment functionFragment = (FunctionFragment) fragment;
            //or functionFragment.getFunction() == ControlSequenceFunctionAlias.SELECT_GRAPHIC_RENDITION
            if (functionFragment.getFunction() == ControlSequenceFunction.SGR) {
                ...
            }
        }
    }

### Thread-safety <a name="ansi4j-core-thread"></a>

`ParserFactory` is thread-safe. `StringParser` and `StreamParser` are not thread-safe. `FunctionFinder`, `TextHandler` and
`FunctionHandler`s are thread-safe. Detailed information about thread-safety is provided in every interface in core API
module.

## CSS extension <a name="ansi4j-css"></a>

### Overview <a name="ansi4j-css-overview"></a>

CSS extension allows to generate CSS declarations one the base of SGR function and its arguments. For example, this
extension can be used to style program outputs, log messages, documentation, and more.

Currently the following text attributes are supported: intensity, italic, underline, blink, reverse video,
visibility, strikethrough, font, foreground color, background color.

### Demo <a name="ansi4j-css-demo"></a>

To work with the CSS extension and understand its principles, we have developed a demo application:

![image info](./css-demo.gif)

To run the demo application, execute the following commands in the project root:

    cd ansi4j-css-demo
    mvn javafx:run

### Base components <a name="ansi4j-css-base"></a>

* `AttributeRegistry` stores model attributes, separated into groups.
* `AttributeGroup` contains a group of logically related attributes. Currently, there is only one group for the SGR function.
* `GroupStyleGenerator` generates CSS declarations based on attribute value changes of a specific group. Thus,
    each generator is used for only one attribute group. Currently, the library has 3 generators for `WebView`, `TextFlow`,
    and RichTextFX `InlineCssTextArea`. These generators are located in the API module and can be easily modified
* `StyleProcessor`, which is called for each SGR function, updates the attribute values and returns the generated
    CSS declarations.
* `Palette` - ISO 6429 supports only 8 colors (3 bits). However, today many terminals supports 4, 8 and 24 bit colors.
However, these extra colors are not included in the standard. So, to support them use `extraColorsEnabled()` method
in config. `Palette` is an interface, so it is easy to add custom 8, 16, 256 color palette.

How it works:

Each attribute in the registry has a default value. Therefore, initially, you need to set values that correspond to
your settings. For example, if the default is italicized text with a red color, you should set the default values for
the `italic` and `fgColor` attributes:

    TextAttributeGroupConfig.Builder()
            .defaultItalic(true)
            .defaultFgColor(new Color(0xff0000))
            ...

When text parsing begins, each SGR function is passed to the `StyleProcessor`. Based on the arguments of this function,
the attribute values are updated. Then, the generator identifies the attributes whose values differ from the default
ones and generates a style for a single span, which will be closed before the next SGR function. Thus, the generated
styles will only be applied to the text between the current SGR function and the next one.

Important notes:

* To enable blink for `WebView` is is necessary to add the following keyframes to your stylesheet:

        @keyframes ansi4j-blinker {50% { opacity: 0; }}.

* RichTextFX `InlineCssTextArea` will support blink when this [issue](https://github.com/FXMisc/RichTextFX/issues/1252)
is resolved.

### Dependencies <a name="ansi4j-css-dependencies"></a>

This project is available on Maven Central:

    <dependency>
        <groupId>com.techsenger.ansi4j</groupId>
        <artifactId>ansi4j-css-api</artifactId>
        <version>2.0.0</version>
    </dependency>
    <dependency>
        <groupId>com.techsenger.ansi4j</groupId>
        <artifactId>ansi4j-css-impl</artifactId>
        <version>2.0.0</version>
    </dependency>

### Usage <a name="ansi4j-css-usage"></a>

    //First of all we create the configuration for text group.
    var palette = new XtermPalette256();
    TextAttributeGroupConfig groupConfig = new TextAttributeGroupConfig.Builder()
            .fontFamilies(List.of("monospace", "Arial"))
            .extraColorsEnabled(true)
            .palette16(palette256)
            .palette256(palette256)
            .build();

    //Now we create a style processor.
    var processor = new StyleProcessor.Builder()
                .configs(groupConfig)
                .generators(new WebViewStyleGenerator())
                .build();
    ...
    //To generate CSS declarations we need to process function fragments. Currently only SGR functions are supported
    Fragment fragment = ... ;
    if (fragment.getType() == FragmentType.FUNCTION) {
        FunctionFragment functionFragment = (FunctionFragment) fragment;
        if (functionFragment.getFunction() == ControlSequenceFunction.SGR) {
            var result = processor.process(functionFragment);
            if (!result.getStyleDeclarations().isEmpty()) {
                var style = String.join(";", result.getStyleDeclarations());
                ...
            }
        }

    }

### Thread-safety <a name="ansi4j-css-thread"></a>

Config, processor, attribute resolver and CSS generators are thread-safe. Attribute context is not thread-safe.
Detailed information about thread-safety is provided in every interface in CSS API module.

## Code building <a name="ansi4j-building"></a>

To build the library use standard Git and Maven commands:

    git clone https://github.com/techsenger/ansi4j.git
    cd ansi4j
    mvn clean install

## Feedback <a name="ansi4j-feedback"></a>

Any feedback is welcome. Besides, it would be interesting to know for what cases this project is used.
It will help to understand the way the project should go and provide more information in documentation.

# Theory <a name="theory"></a>

## Control character <a name="theory-control"></a>

In computing and telecommunication, a control character or non-printing character (NPC) is a code point (a number) in a
character set, that does not represent a written symbol. They are used as in-band signaling to cause effects other than
the addition of a symbol to the text. All other characters are mainly printing, printable, or graphic characters,
except perhaps for the "space" character (see ASCII printable characters). Details are
[here](https://en.wikipedia.org/wiki/Control_character).

## Control character and encoding <a name="theory-encoding"></a>

### Control character in ASCII <a name="theory-encoding-ascii"></a>

All entries in the ASCII table below code 32 (technically the C0 control code set) are control characters, including
CR and LF used to separate lines of text. The code 127 (DEL) is also a control character. Extended ASCII sets defined
by ISO 8859 added the codes 128 through 159 as control characters, this was primarily done so that if the high bit was
stripped it would not change a printing character to a C0 control code, but there have been some assignments here,
in particular NEL. This second set is called the C1 set.

The C0 and C1 control code or control character sets define control codes for use in text by computer systems that use
ASCII and derivatives of ASCII. The codes represent additional information about the text, such as the position of a
cursor, an instruction to start a new line, or a message that the text has been received. Details are
[here](https://en.wikipedia.org/wiki/C0_and_C1_control_codes).

### Control character in Unicode <a name="theory-encoding-unicode"></a>

Many Unicode characters are used to control the interpretation or display of text, but these characters themselves have
no visual or spatial representation. For example, the null character (U+0000 NULL) is used in C-programming application
environments to indicate the end of a string of characters. In this way, these programs only require a single starting
memory address for a string (as opposed to a starting address and a length), since the string ends once the program
reads the null character.

In the narrowest sense, a control code is a character with the general category Cc, which comprises the C0 and C1
control codes, a concept defined in ISO/IEC 2022 and inherited by Unicode, with the most common set being defined in
ISO/IEC 6429. Details are [here](https://en.wikipedia.org/wiki/Unicode_control_characters).

## Control Function Standards <a name="theory-standard"></a>

### ECMA <a name="theory-standard-ecma"></a>

The first edition of this Standard ECMA-48 was published in 1976. Further editions followed. The fourth edition,
published in 1986 was adopted by ISO/IEC under the fast-track procedure as second edition of ISO 6429. It
constitutes a repertoire of a large number of control functions the definitions and coded representations of which are
thus standardized. For each application the required selection of control functions can be made from this repertoire.

This fifth edition of Standard ECMA-48, published in 1991, contains the control functions already standardized in the
fourth edition and, in addition, new control functions needed for handling bi-directional texts, i.e. texts
comprising parts written with a left-to-right script and parts written with a right-to-left script.
ECMA Technical Report TR/53 gives further information and examples of handling such texts. The inclusion of these
specialized control functions has required a corresponding adjustment of the definitions of some of the other control
functions. Moreover, the concept of "device" had to be revised.

The fifth edition was contributed to ISO/IEC for adoption as third edition of ISO/IEC 6429.

### ANSI <a name="theory-standard-ansi"></a>

The name "ANSI escape sequence" dates from 1979 when ANSI adopted ANSI X3.64. The ANSI X3L2 committee collaborated with
the ECMA committee TC 1 to produce nearly identical standards. These two standards were merged into an international
standard, ISO 6429. In 1994, ANSI withdrew its standard in favor of the international standard.

### ISO <a name="theory-standard-iso"></a>

* ISO 6429:1983 (withdrawn),
* ISO 6429:1988(withdrawn),
* ISO/IEC 6429:1992 (current), almost identical to the fifth edition of ECMA-48. There is only one
difference in function set - ISO/IEC 6429:1992 has one extra function DEL-DELETE.

## Types of control functions <a name="theory-types"></a>

Each control function in ECMA-48/5th belongs to one of the following types (section 5.1):

1. elements of the C0 set;
2. elements of the C1 set;
3. control sequences (with parameters and with no parameter);
4. independent control functions;
5. control strings.

## Parameter examples <a name="theory-parameters"></a>

* no params or one default value (see 5.4.2, e)) - CSI H
* one explicit param - CSI 10 H
* two default params - CSI ; H
* two params, the first is 7 and the second is the default value - CSI 7; H
* two params, the first is the default value and the second is 7 - CSI ;7 H
* three params, the first is 1, the second is the default value and the third is 4 - CSI 1;;4 H
* two explicit params - CSI 10 ; 20 H
* more explicit params - CSI 1 ; 2 ; 3 ; 4 ; 5 H

Note: spaces near parameters are not used in real function code.

# Useful resources <a name="resources"></a>

* [ECMA-48](https://www.ecma-international.org/publications-and-standards/standards/ecma-48/)
* [ISO/IEC 6429:1992 - preview](https://www.iso.org/obp/ui/#iso:std:iso-iec:6429:ed-3:v1:en)
* [ANSI-X3.64](http://www.noah.org/python/pexpect/ANSI-X3.64.htm)
* [XTerm Control Sequences](https://invisible-island.net/xterm/ctlseqs/ctlseqs.html)
