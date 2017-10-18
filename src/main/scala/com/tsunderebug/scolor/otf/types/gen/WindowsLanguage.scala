package com.tsunderebug.scolor.otf.types.gen

import spire.math.UShort

object WindowsLanguage {

  class Dialect(val i: UShort)

  implicit def intToUShort(i: Int): UShort = UShort(i)

  object `Afrikaans` {

    case object `South Africa` extends Dialect(0x0436)

  }

  object `Albanian` {

    case object `Albania` extends Dialect(0x041C)

  }

  object `Alsatian` {

    case object `France` extends Dialect(0x0484)

  }

  object `Amharic` {

    case object `Ethiopia` extends Dialect(0x045E)

  }

  object `Arabic` {

    case object `Algeria` extends Dialect(0x1401)

    case object `Bahrain` extends Dialect(0x3C01)

    case object `Egypt` extends Dialect(0x0C01)

    case object `Iraq` extends Dialect(0x0801)

    case object `Jordan` extends Dialect(0x2C01)

    case object `Kuwait` extends Dialect(0x3401)

    case object `Lebanon` extends Dialect(0x3001)

    case object `Libya` extends Dialect(0x1001)

    case object `Morocco` extends Dialect(0x1801)

    case object `Oman` extends Dialect(0x2001)

    case object `Qatar` extends Dialect(0x4001)

    case object `Saudi Arabia` extends Dialect(0x0401)

    case object `Syria` extends Dialect(0x2801)

    case object `Tunisia` extends Dialect(0x1C01)

    case object `U.A.E.` extends Dialect(0x3801)

    case object `Yemen` extends Dialect(0x2401)

  }

  object `Armenian` {

    case object `Armenia` extends Dialect(0x042B)

  }

  object `Assamese` {

    case object `India` extends Dialect(0x044D)

  }

  object `Azeri (Cyrillic)` {

    case object `Azerbaijan` extends Dialect(0x082C)

  }

  object `Azeri (Latin)` {

    case object `Azerbaijan` extends Dialect(0x042C)

  }

  object `Bashkir` {

    case object `Russia` extends Dialect(0x046D)

  }

  object `Basque` {

    case object `Basque` extends Dialect(0x042D)

  }

  object `Belarusian` {

    case object `Belarus` extends Dialect(0x0423)

  }

  object `Bengali` {

    case object `Bangladesh` extends Dialect(0x0845)

    case object `India` extends Dialect(0x0445)

  }

  object `Bosnian (Cyrillic)` {

    case object `Bosnia and Herzegovina` extends Dialect(0x201A)

  }

  object `Bosnian (Latin)` {

    case object `Bosnia and Herzegovina` extends Dialect(0x141A)

  }

  object `Breton` {

    case object `France` extends Dialect(0x047E)

  }

  object `Bulgarian` {

    case object `Bulgaria` extends Dialect(0x0402)

  }

  object `Catalan` {

    case object `Catalan` extends Dialect(0x0403)

  }

  object `Chinese` {

    case object `Hong Kong S.A.R.` extends Dialect(0x0C04)

    case object `Macao S.A.R.` extends Dialect(0x1404)

    case object `People's Republic of China` extends Dialect(0x0804)

    case object `Singapore` extends Dialect(0x1004)

    case object `Taiwan` extends Dialect(0x0404)

  }

  object `Corsican` {

    case object `France` extends Dialect(0x0483)

  }

  object `Croatian` {

    case object `Croatia` extends Dialect(0x041A)

  }

  object `Croatian (Latin)` {

    case object `Bosnia and Herzegovina` extends Dialect(0x101A)

  }

  object `Czech` {

    case object `Czech Republic` extends Dialect(0x0405)

  }

  object `Danish` {

    case object `Denmark` extends Dialect(0x0406)

  }

  object `Dari` {

    case object `Afghanistan` extends Dialect(0x048C)

  }

  object `Divehi` {

    case object `Maldives` extends Dialect(0x0465)

  }

  object `Dutch` {

    case object `Belgium` extends Dialect(0x0813)

    case object `Netherlands` extends Dialect(0x0413)

  }

  object `English` {

    case object `Australia` extends Dialect(0x0C09)

    case object `Belize` extends Dialect(0x2809)

    case object `Canada` extends Dialect(0x1009)

    case object `Caribbean` extends Dialect(0x2409)

    case object `India` extends Dialect(0x4009)

    case object `Ireland` extends Dialect(0x1809)

    case object `Jamaica` extends Dialect(0x2009)

    case object `Malaysia` extends Dialect(0x4409)

    case object `New Zealand` extends Dialect(0x1409)

    case object `Republic of the Philippines` extends Dialect(0x3409)

    case object `Singapore` extends Dialect(0x4809)

    case object `South Africa` extends Dialect(0x1C09)

    case object `Trinidad and Tobago` extends Dialect(0x2C09)

    case object `United Kingdom` extends Dialect(0x0809)

    case object `United States` extends Dialect(0x0409)

    case object `Zimbabwe` extends Dialect(0x3009)

  }

  object `Estonian` {

    case object `Estonia` extends Dialect(0x0425)

  }

  object `Faroese` {

    case object `Faroe Islands` extends Dialect(0x0438)

  }

  object `Filipino` {

    case object `Philippines` extends Dialect(0x0464)

  }

  object `Finnish` {

    case object `Finland` extends Dialect(0x040B)

  }

  object `French` {

    case object `Belgium` extends Dialect(0x080C)

    case object `Canada` extends Dialect(0x0C0C)

    case object `France` extends Dialect(0x040C)

    case object `Luxembourg` extends Dialect(0x140C)

    case object `Principality of Monaco` extends Dialect(0x180C)

    case object `Switzerland` extends Dialect(0x100C)

  }

  object `Frisian` {

    case object `Netherlands` extends Dialect(0x0462)

  }

  object `Galician` {

    case object `Galician` extends Dialect(0x0456)

  }

  object `Georgian` {

    case object `Georgia` extends Dialect(0x0437)

  }

  object `German` {

    case object `Austria` extends Dialect(0x0C07)

    case object `Germany` extends Dialect(0x0407)

    case object `Liechtenstein` extends Dialect(0x1407)

    case object `Luxembourg` extends Dialect(0x1007)

    case object `Switzerland` extends Dialect(0x0807)

  }

  object `Greek` {

    case object `Greece` extends Dialect(0x0408)

  }

  object `Greenlandic` {

    case object `Greenland` extends Dialect(0x046F)

  }

  object `Gujarati` {

    case object `India` extends Dialect(0x0447)

  }

  object `Hausa (Latin)` {

    case object `Nigeria` extends Dialect(0x0468)

  }

  object `Hebrew` {

    case object `Israel` extends Dialect(0x040D)

  }

  object `Hindi` {

    case object `India` extends Dialect(0x0439)

  }

  object `Hungarian` {

    case object `Hungary` extends Dialect(0x040E)

  }

  object `Icelandic` {

    case object `Iceland` extends Dialect(0x040F)

  }

  object `Igbo` {

    case object `Nigeria` extends Dialect(0x0470)

  }

  object `Indonesian` {

    case object `Indonesia` extends Dialect(0x0421)

  }

  object `Inuktitut` {

    case object `Canada` extends Dialect(0x045D)

  }

  object `Inuktitut (Latin)` {

    case object `Canada` extends Dialect(0x085D)

  }

  object `Irish` {

    case object `Ireland` extends Dialect(0x083C)

  }

  object `isiXhosa` {

    case object `South Africa` extends Dialect(0x0434)

  }

  object `isiZulu` {

    case object `South Africa` extends Dialect(0x0435)

  }

  object `Italian` {

    case object `Italy` extends Dialect(0x0410)

    case object `Switzerland` extends Dialect(0x0810)

  }

  object `Japanese` {

    case object `Japan` extends Dialect(0x0411)

  }

  object `Kannada` {

    case object `India` extends Dialect(0x044B)

  }

  object `Kazakh` {

    case object `Kazakhstan` extends Dialect(0x043F)

  }

  object `Khmer` {

    case object `Cambodia` extends Dialect(0x0453)

  }

  object `K'iche` {

    case object `Guatemala` extends Dialect(0x0486)

  }

  object `Kinyarwanda` {

    case object `Rwanda` extends Dialect(0x0487)

  }

  object `Kiswahili` {

    case object `Kenya` extends Dialect(0x0441)

  }

  object `Konkani` {

    case object `India` extends Dialect(0x0457)

  }

  object `Korean` {

    case object `Korea` extends Dialect(0x0412)

  }

  object `Kyrgyz` {

    case object `Kyrgyzstan` extends Dialect(0x0440)

  }

  object `Lao` {

    case object `Lao P.D.R.` extends Dialect(0x0454)

  }

  object `Latvian` {

    case object `Latvia` extends Dialect(0x0426)

  }

  object `Lithuanian` {

    case object `Lithuania` extends Dialect(0x0427)

  }

  object `Lower Sorbian` {

    case object `Germany` extends Dialect(0x082E)

  }

  object `Luxembourgish` {

    case object `Luxembourg` extends Dialect(0x046E)

  }

  object `Macedonian (FYROM)` {

    case object `Former Yugoslav Republic of Macedonia` extends Dialect(0x042F)

  }

  object `Malay` {

    case object `Brunei Darussalam` extends Dialect(0x083E)

    case object `Malaysia` extends Dialect(0x043E)

  }

  object `Malayalam` {

    case object `India` extends Dialect(0x044C)

  }

  object `Maltese` {

    case object `Malta` extends Dialect(0x043A)

  }

  object `Maori` {

    case object `New Zealand` extends Dialect(0x0481)

  }

  object `Mapudungun` {

    case object `Chile` extends Dialect(0x047A)

  }

  object `Marathi` {

    case object `India` extends Dialect(0x044E)

  }

  object `Mohawk` {

    case object `Mohawk` extends Dialect(0x047C)

  }

  object `Mongolian (Cyrillic)` {

    case object `Mongolia` extends Dialect(0x0450)

  }

  object `Mongolian (Traditional)` {

    case object `People's Republic of China` extends Dialect(0x0850)

  }

  object `Nepali` {

    case object `Nepal` extends Dialect(0x0461)

  }

  object `Norwegian (Bokmal)` {

    case object `Norway` extends Dialect(0x0414)

  }

  object `Norwegian (Nynorsk)` {

    case object `Norway` extends Dialect(0x0814)

  }

  object `Occitan` {

    case object `France` extends Dialect(0x0482)

  }

  object `Odia (formerly Oriya)` {

    case object `India` extends Dialect(0x0448)

  }

  object `Pashto` {

    case object `Afghanistan` extends Dialect(0x0463)

  }

  object `Polish` {

    case object `Poland` extends Dialect(0x0415)

  }

  object `Portuguese` {

    case object `Brazil` extends Dialect(0x0416)

    case object `Portugal` extends Dialect(0x0816)

  }

  object `Punjabi` {

    case object `India` extends Dialect(0x0446)

  }

  object `Quechua` {

    case object `Bolivia` extends Dialect(0x046B)

    case object `Ecuador` extends Dialect(0x086B)

    case object `Peru` extends Dialect(0x0C6B)

  }

  object `Romanian` {

    case object `Romania` extends Dialect(0x0418)

  }

  object `Romansh` {

    case object `Switzerland` extends Dialect(0x0417)

  }

  object `Russian` {

    case object `Russia` extends Dialect(0x0419)

  }

  object `Sami (Inari)` {

    case object `Finland` extends Dialect(0x243B)

  }

  object `Sami (Lule)` {

    case object `Norway` extends Dialect(0x103B)

    case object `Sweden` extends Dialect(0x143B)

  }

  object `Sami (Northern)` {

    case object `Finland` extends Dialect(0x0C3B)

    case object `Norway` extends Dialect(0x043B)

    case object `Sweden` extends Dialect(0x083B)

  }

  object `Sami (Skolt)` {

    case object `Finland` extends Dialect(0x203B)

  }

  object `Sami (Southern)` {

    case object `Norway` extends Dialect(0x183B)

    case object `Sweden` extends Dialect(0x1C3B)

  }

  object `Sanskrit` {

    case object `India` extends Dialect(0x044F)

  }

  object `Serbian (Cyrillic)` {

    case object `Bosnia and Herzegovina` extends Dialect(0x1C1A)

    case object `Serbia` extends Dialect(0x0C1A)

  }

  object `Serbian (Latin)` {

    case object `Bosnia and Herzegovina` extends Dialect(0x181A)

    case object `Serbia` extends Dialect(0x081A)

  }

  object `Sesotho sa Leboa` {

    case object `South Africa` extends Dialect(0x046C)

  }

  object `Setswana` {

    case object `South Africa` extends Dialect(0x0432)

  }

  object `Sinhala` {

    case object `Sri Lanka` extends Dialect(0x045B)

  }

  object `Slovak` {

    case object `Slovakia` extends Dialect(0x041B)

  }

  object `Slovenian` {

    case object `Slovenia` extends Dialect(0x0424)

  }

  object `Spanish` {

    case object `Argentina` extends Dialect(0x2C0A)

    case object `Bolivia` extends Dialect(0x400A)

    case object `Chile` extends Dialect(0x340A)

    case object `Colombia` extends Dialect(0x240A)

    case object `Costa Rica` extends Dialect(0x140A)

    case object `Dominican Republic` extends Dialect(0x1C0A)

    case object `Ecuador` extends Dialect(0x300A)

    case object `El Salvador` extends Dialect(0x440A)

    case object `Guatemala` extends Dialect(0x100A)

    case object `Honduras` extends Dialect(0x480A)

    case object `Mexico` extends Dialect(0x080A)

    case object `Nicaragua` extends Dialect(0x4C0A)

    case object `Panama` extends Dialect(0x180A)

    case object `Paraguay` extends Dialect(0x3C0A)

    case object `Peru` extends Dialect(0x280A)

    case object `Puerto Rico` extends Dialect(0x500A)

    case object `United States` extends Dialect(0x540A)

    case object `Uruguay` extends Dialect(0x380A)

    case object `Venezuela` extends Dialect(0x200A)

  }

  object `Spanish (Modern Sort)` {

    case object `Spain` extends Dialect(0x0C0A)

  }

  object `Spanish (Traditional Sort)` {

    case object `Spain` extends Dialect(0x040A)

  }

  object `Sweden` {

    case object `Finland` extends Dialect(0x081D)

  }

  object `Swedish` {

    case object `Sweden` extends Dialect(0x041D)

  }

  object `Syriac` {

    case object `Syria` extends Dialect(0x045A)

  }

  object `Tajik (Cyrillic)` {

    case object `Tajikistan` extends Dialect(0x0428)

  }

  object `Tamazight (Latin)` {

    case object `Algeria` extends Dialect(0x085F)

  }

  object `Tamil` {

    case object `India` extends Dialect(0x0449)

  }

  object `Tatar` {

    case object `Russia` extends Dialect(0x0444)

  }

  object `Telugu` {

    case object `India` extends Dialect(0x044A)

  }

  object `Thai` {

    case object `Thailand` extends Dialect(0x041E)

  }

  object `Tibetan` {

    case object `PRC` extends Dialect(0x0451)

  }

  object `Turkish` {

    case object `Turkey` extends Dialect(0x041F)

  }

  object `Turkmen` {

    case object `Turkmenistan` extends Dialect(0x0442)

  }

  object `Uighur` {

    case object `PRC` extends Dialect(0x0480)

  }

  object `Ukrainian` {

    case object `Ukraine` extends Dialect(0x0422)

  }

  object `Upper Sorbian` {

    case object `Germany` extends Dialect(0x042E)

  }

  object `Urdu` {

    case object `Islamic Republic of Pakistan` extends Dialect(0x0420)

  }

  object `Uzbek (Cyrillic)` {

    case object `Uzbekistan` extends Dialect(0x0843)

  }

  object `Uzbek (Latin)` {

    case object `Uzbekistan` extends Dialect(0x0443)

  }

  object `Vietnamese` {

    case object `Vietnam` extends Dialect(0x042A)

  }

  object `Welsh` {

    case object `United Kingdom` extends Dialect(0x0452)

  }

  object `Wolof` {

    case object `Senegal` extends Dialect(0x0488)

  }

  object `Yakut` {

    case object `Russia` extends Dialect(0x0485)

  }

  object `Yi` {

    case object `PRC` extends Dialect(0x0478)

  }

  object `Yoruba` {

    case object `Nigeria` extends Dialect(0x046A)

  }

}
