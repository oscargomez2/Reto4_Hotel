package mintic.misiontic.usa.ciclo3.grupo5.equipo2.SistemaHotelesAPI.servicios;

import java.util.List;
import java.util.Optional;
import mintic.misiontic.usa.ciclo3.grupo5.equipo2.SistemaHotelesAPI.modelos.Reservation;
import mintic.misiontic.usa.ciclo3.grupo5.equipo2.SistemaHotelesAPI.repositorios.RepositorioReservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioReservation {

    @Autowired
    private RepositorioReservation metodosCrud;

    public List<Reservation> getAll() {
        return metodosCrud.getAll();
    }

    public Optional<Reservation> getReservation(int id) {
        return metodosCrud.getReservation(id);
    }

    public void save(Reservation categoria) {
        if (categoria.getIdReservation() == null) {
            metodosCrud.save(categoria);
        } else {
            Optional<Reservation> evt = metodosCrud.getReservation(categoria.getIdReservation());
            if (evt.isEmpty()) {
                metodosCrud.save(categoria);
            }
        }
    }

    public void update(Reservation reservation) {
        if (reservation.getIdReservation() != null) {
            Optional<Reservation> obtener = metodosCrud.getReservation(reservation.getIdReservation());
            if (!obtener.isEmpty()) {
                if (reservation.getStartDate() != null) {
                    obtener.get().setStartDate(reservation.getStartDate());
                }
                if (reservation.getDevolutionDate() != null) {
                    obtener.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                metodosCrud.save(obtener.get());
            }
        }
    }

    public boolean delete(int id) {
        Optional<Reservation> obtener = metodosCrud.getReservation(id);
        if (!obtener.isEmpty()) {
            metodosCrud.delete(obtener.get());
            return true;
        }
        return false;
    }
}
