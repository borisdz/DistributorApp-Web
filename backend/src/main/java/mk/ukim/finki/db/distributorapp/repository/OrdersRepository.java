package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.dto.OrderManagerDto;
import mk.ukim.finki.db.distributorapp.model.dto.OrderSimpleDto;
import mk.ukim.finki.db.distributorapp.model.dto.OrdersDto;
import mk.ukim.finki.db.distributorapp.model.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    @Query(
            nativeQuery = true,
            value = """
                    select o.ord_id as id,
                           o.ord_date as ordDate,
                           o.ord_sum as ordSum,
                           o.ord_fulfillment_date as ordFulfillmentDate,
                           o.ord_comment as ordComment,
                           o.o_status_id as oStatusId,
                           os.o_status_name as statusName,
                           o.cust_id as customerId,
                           c.cust_company_name as customerName,
                           u.user_mobile as customerPhone,
                           u.user_email as customerEmail,
                           o.del_id as deliveryId,
                           d.user_id as driverId,
                           u1.user_name as driverName,
                           u1.user_mobile as driverPhone,
                           u1.user_email as driverEmail,
                           o.pf_id as pfId,
                           pfs.pf_status_name as pfStatus
                    from orders o
                    join order_status os on o.o_status_id = os.o_status_id
                    join customer c on c.user_id=o.cust_id
                    join users u on c.user_id=u.user_id
                    join delivery del on o.del_id = del.del_id
                    join vehicle v on del.veh_id = v.veh_id
                    join driver d on d.veh_id=v.veh_id
                    join users u1 on d.user_id=u1.user_id
                    join pro_forma pf on o.pf_id = pf.pf_id
                    join pro_forma_status pfs on pfs.pf_status_id=pf.pf_status_id
                    """
    )
    List<OrdersDto> listAll();

    @Query(
            nativeQuery = true,
            value = """
                    select o.ord_id as id,
                           o.ord_date as ordDate,
                           o.ord_sum as ordSum,
                           o.ord_fulfillment_date as ordFulfillmentDate,
                           o.ord_comment as ordComment,
                           o.o_status_id as oStatusId,
                           os.o_status_name as statusName,
                           o.cust_id as customerId,
                           c.cust_company_name as customerName,
                           u.user_mobile as customerPhone,
                           u.user_email as customerEmail,
                           del.del_id as deliveryId,
                           d.user_id as driverId,
                           u1.user_name as driverName,
                           u1.user_mobile as driverPhone,
                           u1.user_email as driverEmail,
                           pf.pf_id as pfId,
                           pfs.pf_status_name as pfStatus
                    from orders o
                    join order_status os on o.o_status_id = os.o_status_id
                    join customer c on o.cust_id = c.user_id
                    join users u on c.user_id = u.user_id
                    join delivery del on o.del_id = del.del_id
                    join vehicle v on del.veh_id = v.veh_id
                    join driver d on d.veh_id=v.veh_id
                    join users u1 on d.user_id=u1.user_id
                    join pro_forma pf on o.pf_id = pf.pf_id
                    join pro_forma_status pfs on pfs.pf_status_id=pf.pf_status_id
                    where o.cust_id = ?1
                    """
    )
    List<OrdersDto> findOrdersByCustomer(@NonNull Long id);

    @Query(
            nativeQuery = true,
            value = """
                    select o.ord_id as id,
                           o.ord_date as ordDate,
                           o.ord_sum as ordSum,
                           o.ord_fulfillment_date as ordFulfillmentDate,
                           o.ord_comment as ordComment,
                           o.o_status_id as oStatusId,
                           o.cust_id as customerId,
                           o.del_id as deliveryId,
                           o.pf_id as pfId
                    from orders o
                    where o.cust_id = ?1
                    """
    )
    List<OrderSimpleDto> findSimpleOrdersByCustomer(@NonNull Long id);

    @Query(
            nativeQuery = true,
            value = "select * from orders where ord_id=?1"
    )
    OrdersDto findOrderById(@NonNull Long id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "insert into orders (ord_date, ord_sum, ord_fulfillment_date, ord_comment, o_status_id, cust_id, del_id, pf_id) " +
                    "values (?1,?2,?3,?4,?5,?6,?7,?8)"
    )
    Integer create(
            @NonNull LocalDate ord_date,
            @NonNull Integer ord_sum,
            LocalDateTime ord_fulfillment_date,
            String ord_comment,
            @NonNull Short ord_status_id,
            @NonNull Long cust_id,
            Long del_id,
            Long pf_id
    );

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "update orders " +
                    "set ord_date=?2,ord_sum=?3,ord_fulfillment_date=?4,ord_comment=?5,o_status_id=?6,cust_id=?7,del_id=?8,pf_id=?9 " +
                    "where ord_id=?1"
    )
    Integer edit(
            @NonNull Long id,
            @NonNull LocalDate ord_date,
            @NonNull Integer ord_sum,
            LocalDateTime ord_fulfillment_date,
            String ord_comment,
            @NonNull Short ord_status_id,
            @NonNull Long cust_id,
            @NonNull Long del_id,
            @NonNull Long pf_id
    );

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "delete from orders where ord_id=?1"
    )
    void delete(@NonNull Long id);
    //----------------------------------------------------------------------------------------------------------------------------------

    @Query(
            nativeQuery = true,
            value = """
                    select o.ord_id as id,
                           o.ord_date as ordDate,
                           o.ord_sum as ordSum,
                           o.ord_fulfillment_date as ordFulfillmentDate,
                           o.ord_comment as ordComment,
                           o.o_status_id as oStatusId,
                           os.o_status_name as statusName,
                           o.cust_id as customerId,
                           c.cust_company_name as customerName,
                           u.user_mobile as customerPhone,
                           u.user_email as customerEmail,
                           o.del_id as deliveryId,
                           d.user_id as driverId,
                           u1.user_name as driverName,
                           u1.user_mobile as driverPhone,
                           u1.user_email as driverEmail,
                           o.pf_id as pfId,
                           pfs.pf_status_name as pfStatus
                    from orders o
                    join order_status os on o.o_status_id = os.o_status_id
                    join customer c on c.user_id=o.cust_id
                    join users u on c.user_id=u.user_id
                    join delivery del on o.del_id = del.del_id
                    join vehicle v on del.veh_id = v.veh_id
                    join driver d on d.veh_id=v.veh_id
                    join users u1 on d.user_id=u1.user_id
                    join pro_forma pf on o.pf_id = pf.pf_id
                    join pro_forma_status pfs on pfs.pf_status_id=pf.pf_status_id
                    where o.cust_id=:customer and o.o_status_id between 2 and 6
                    """
    )
    List<OrdersDto> getCurrentOrdersByCustomer(@NonNull @Param("customer") Long customer_id);

//    @Query(
//            nativeQuery = true,
//            value = """
//                    select o.ord_id as id,
//                           o.ord_date as ordDate,
//                           o.ord_sum as ordSum,
//                           o.ord_fulfillment_date as ordFulfillmentDate,
//                           o.ord_comment as ordComment,
//                           o.o_status_id as oStatusId,
//                           os.o_status_name as statusName,
//                           c.user_id as customerId,
//                           c.cust_company_name as customerName,
//                           u.user_mobile as customerPhone,
//                           u.user_email as customerEmail,
//                           null as deliveryid,
//                           null as driverId,
//                           null as driverName,
//                           null as driverPhone,
//                           null as driverEmail,
//                           pf.pf_status_id as pfId,
//                           pfs.pf_status_name as pfStatus
//                    from warehouse w
//                        join manager m on w.wh_id= m.wh_id
//                        join article_unit au on au.wh_id = w.wh_id
//                        join orders o on au.ord_id = o.ord_id
//                        join order_status os on os.o_status_id = o.o_status_id
//                        join customer c on o.cust_id = c.user_id
//                        join users u on c.user_id = u.user_id
//                        join pro_forma pf on o.pf_id = pf.pf_id
//                        join pro_forma_status pfs on pf.pf_status_id=pfs.pf_status_id
//                    where m.user_id = :manager
//                      and o.o_status_id = 1
//                    order by o.ord_date desc
//                    """
//    )
//    List<OrdersDto> getNewOrdersByManager(@NonNull @Param("manager") Long manager_id);

    @Query(
            nativeQuery = true,
            value = """
                    select o.ord_id as id,
                           o.ord_date as ordDate,
                           o.ord_sum as ordSum,
                           o.ord_fulfillment_date as ordFulfillmentDate,
                           o.ord_comment as ordComment,
                           o.o_status_id as oStatusId,
                           o.cust_id as customerId,
                           c.cust_company_name as customerName,
                           o.del_id as deliveryId,
                           o.pf_id as pfId
                    from orders o
                    join customer c on o.cust_id = c.user_id
                    join article_unit au on o.ord_id = au.ord_id
                    join warehouse w on au.wh_id = w.wh_id
                    join manager m on w.wh_id = m.wh_id
                    where m.user_id = ?1
                    """
    )
    List<OrderManagerDto> getNewOrdersByManager(@NonNull @Param("manager") Long manager_id);
}