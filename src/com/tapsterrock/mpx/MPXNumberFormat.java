/*
 * file:       MPXNumberFormat.java
 * author:     Scott Melville
 *             Jon Iles
 * copyright:  (c) Tapster Rock Limited 2002-2003
 * date:       15/08/2002
 */

/*
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation; either version 2.1 of the License, or (at your
 * option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307, USA.
 */

package com.tapsterrock.mpx;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParsePosition;

/**
 * This class extends the functionality of the DecimalFormat class
 * for use within MPXJ.
 */
final class MPXNumberFormat extends DecimalFormat
{
   /**
    * Default constructor.
    */
   MPXNumberFormat ()
   {

   }

   /**
    * Constructor allowing primary format pattern to be supplied.
    *
    * @param primaryPattern new format pattern
    * @param decimalSeparator Locale specific decimal separator to replace placeholder
    * @param groupingSeparator Locale specific grouping separator to replace placeholder    *
    */
   MPXNumberFormat (String primaryPattern, char decimalSeparator, char groupingSeparator)
   {
      applyPattern (primaryPattern, null, decimalSeparator, groupingSeparator);
   }

   /**
    * Constructor allowing primary format and alternative format
    * patterns to be supplied.
    *
    * @param primaryPattern new format pattern
    * @param alternativePatterns alternative format patterns
    * @param decimalSeparator Locale specific decimal separator to replace placeholder
    * @param groupingSeparator Locale specific grouping separator to replace placeholder    *
    */
   MPXNumberFormat (String primaryPattern, String[] alternativePatterns, char decimalSeparator, char groupingSeparator)
   {
      applyPattern (primaryPattern, alternativePatterns, decimalSeparator, groupingSeparator);
   }

   /**
    * This method is used to configure the primary and alternative
    * format patterns.
    *
    * @param primaryPattern new format pattern
    * @param alternativePatterns alternative format patterns
    * @param decimalSeparator Locale specific decimal separator to replace placeholder
    * @param groupingSeparator Locale specific grouping separator to replace placeholder
    */
   public void applyPattern (String primaryPattern, String[] alternativePatterns, char decimalSeparator, char groupingSeparator)
   {
      m_symbols.setDecimalSeparator(decimalSeparator);
      m_symbols.setGroupingSeparator(groupingSeparator);

      setDecimalFormatSymbols(m_symbols);
      applyPattern (primaryPattern);

      if (alternativePatterns != null && alternativePatterns.length != 0)
      {
         int loop;
         if (m_alternativeFormats == null || m_alternativeFormats.length != alternativePatterns.length)
         {
            m_alternativeFormats = new DecimalFormat[alternativePatterns.length];
            for (loop=0; loop < alternativePatterns.length; loop++)
            {
               m_alternativeFormats[loop] = new DecimalFormat();
            }
         }

         for (loop=0; loop < alternativePatterns.length; loop++)
         {
            m_alternativeFormats[loop].setDecimalFormatSymbols(m_symbols);
            m_alternativeFormats[loop].applyPattern(alternativePatterns[loop]);
         }
      }

      m_primaryPattern = primaryPattern;
   }

   /**
    * {@inheritDoc}
    */
   public Number parse (String str, ParsePosition parsePosition)
   {
      Number result = null;

      if (str == null)
      {
         parsePosition.setIndex(-1);         
      }
      else
      {
         str = str.trim();
         
         if (str.length() == 0)
         {
            parsePosition.setIndex(-1);            
         }
         else
         {
            result = super.parse(str, parsePosition);
            if (parsePosition.getIndex() == 0)
            {
               result = null;
               
               if (m_alternativeFormats != null)
               {
                  for (int loop=0; loop < m_alternativeFormats.length; loop++)
                  {
                     result = m_alternativeFormats[loop].parse(str, parsePosition);
                     if (parsePosition.getIndex() != 0)
                     {
                        break;
                     }
                  }
   
                  if (parsePosition.getIndex() == 0)
                  {
                     result = null;
                  }
               }
            }            
         }
      }

      return (result);
   }

   /**
    * Number formatter.
    */
   private DecimalFormatSymbols m_symbols = new DecimalFormatSymbols ();
   private DecimalFormat[] m_alternativeFormats;
   private String m_primaryPattern = "";
}
