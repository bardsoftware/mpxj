/*
 * file:       ResourceAssignmentWorkgroupFields.java
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

import java.util.Date;


/**
 * This class represents a resorce assignment workgrouo fields record
 * from an MPX file.
 */
public final class ResourceAssignmentWorkgroupFields extends MPXRecord
{
   /**
    * Default constructor.
    *
    * @param file the parent file to which this record belongs.
    * @throws MPXException normally thrown when parsing fails
    */
   ResourceAssignmentWorkgroupFields (ProjectFile file)
      throws MPXException
   {
      super(file);
   }

   /**
    * Constructor used to create an instance of this class from data
    * taken from an MPXFile record.
    *
    * @param file the MPXFile object to which this record belongs.
    * @param record record containing the data for  this object.
    * @throws MPXException normally thrown when parsing fails
    */
   ResourceAssignmentWorkgroupFields (ProjectFile file, Record record)
      throws MPXException
   {
      super(file);

      setMessageUniqueID(record.getString(0));
      setConfirmed(NumberUtility.getInt(record.getInteger(1))==1);
      setResponsePending(NumberUtility.getInt(record.getInteger(1))==1);
      setUpdateStart(record.getDateTime(3));
      setUpdateFinish(record.getDateTime(4));
      setScheduleID(record.getString(5));
   }

   /**
    * Sets the Message Unique ID.
    *
    * @param val ID
    */
   public void setMessageUniqueID (String val)
   {
      m_messageUniqueID = val;
   }

   /**
    * Gets the Message Unique ID.
    *
    * @return ID
    */
   public String getMessageUniqueID ()
   {
      return (m_messageUniqueID);
   }

   /**
    * Gets confirmed flag.
    *
    * @return boolean value
    */
   public boolean getConfirmed ()
   {
      return (m_confirmed);
   }

   /**
    * Sets confirmed flag.
    *
    * @param val boolean flag
    */
   public void setConfirmed (boolean val)
   {
      m_confirmed = val;
   }

   /**
    * Sets response pending flag.
    *
    * @param val boolean flag
    */
   public void setResponsePending (boolean val)
   {
      m_responsePending = val;
   }

   /**
    * Retrieves response pending flag.
    *
    * @return boolean flag
    */
   public boolean getResponsePending ()
   {
      return (m_responsePending);
   }

   /**
    * Sets the Update Start Field.
    *
    * @param val date to set
    */
   public void setUpdateStart (Date val)
   {
      m_updateStart = val;
   }

   /**
    * Gets the Update Start Field value.
    *
    * @return update Start Date
    */
   public Date getUpdateStart ()
   {
      return (m_updateStart);
   }

   /**
    * Sets the Update Finish Field.
    * 
    * @param val date to set
    */
   public void setUpdateFinish (Date val)
   {
      m_updateFinish = val;
   }

   /**
    * Gets the Update Finish Field value.
    *
    * @return update Finish Date
    */
   public Date getUpdateFinish ()
   {
      return (m_updateFinish);
   }

   /**
    * Sets the schedule ID.
    *
    * @param val schedule ID
    */
   public void setScheduleID (String val)
   {
      m_scheduleID = val;
   }

   /**
    * Retrieves the schedule ID.
    *
    * @return schedule ID
    */
   public String getScheduleID ()
   {
      return (m_scheduleID);
   }

   private String m_messageUniqueID;
   private boolean m_confirmed;
   private boolean m_responsePending;
   private Date m_updateStart;
   private Date m_updateFinish;
   private String m_scheduleID;
}
